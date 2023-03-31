package com.juarezcode.myjobs.ui.vacante

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.juarezcode.myjobs.data.local.PreferenciasLocales
import com.juarezcode.myjobs.data.models.Vacante
import com.juarezcode.myjobs.databinding.ItemJobBinding

class VacantesAdapter(
    private val onClickPostular: (Vacante) -> Unit
) : ListAdapter<Vacante, VacantesAdapter.JobsViewHolder>(DiffCallback) {

    class JobsViewHolder(private val binding: ItemJobBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val usuarioEnSesion =
            PreferenciasLocales.getInstance(binding.root.context).obtenerUsuarioEnSesion()

        fun bind(vacante: Vacante, onClickPostular: (Vacante) -> Unit) {
            binding.itemJobTxtNombreVacante.text = vacante.nombre
            binding.itemJobTxtDescripcionVacante.text = vacante.descripcion

            if (usuarioEnSesion.esAdministrador) {
                binding.itemJobBotonPostularme.isVisible = false
            } else {
                binding.itemJobBotonPostularme.isVisible = true
                binding.itemJobBotonPostularme.setOnClickListener {
                    onClickPostular(vacante)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobsViewHolder {
        val binding = ItemJobBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JobsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JobsViewHolder, position: Int) {
        val vacante = getItem(position)
        holder.bind(vacante, onClickPostular)
    }

    object DiffCallback : DiffUtil.ItemCallback<Vacante>() {
        override fun areItemsTheSame(oldItem: Vacante, newItem: Vacante) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Vacante, newItem: Vacante) =
            oldItem == newItem
    }
}