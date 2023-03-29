package com.juarezcode.myjobs.ui.job

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.juarezcode.myjobs.data.models.Vacante
import com.juarezcode.myjobs.databinding.ItemJobBinding

class JobsAdapter : ListAdapter<Vacante, JobsAdapter.JobsViewHolder>(DiffCallback) {

    class JobsViewHolder(private val binding: ItemJobBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(vacante: Vacante) {
            binding.txtItemJobNombreVacante.text = vacante.nombre
            binding.txtItemJobDescripcionVacante.text = vacante.descripcion
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobsViewHolder {
        val binding = ItemJobBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JobsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JobsViewHolder, position: Int) {
        val vacante = getItem(position)
        holder.bind(vacante)
    }

    object DiffCallback : DiffUtil.ItemCallback<Vacante>() {
        override fun areItemsTheSame(oldItem: Vacante, newItem: Vacante) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Vacante, newItem: Vacante) =
            oldItem == newItem
    }
}