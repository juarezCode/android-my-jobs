package com.juarezcode.myjobs.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.juarezcode.myjobs.data.models.Postulacion
import com.juarezcode.myjobs.databinding.ItemPostulacionBinding

class PostulacionesAdapter :
    ListAdapter<Postulacion, PostulacionesAdapter.PostulacionesViewHolder>(DiffCallback) {

    class PostulacionesViewHolder(private val binding: ItemPostulacionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(postulacion: Postulacion) = with(binding) {
            itemRequestTxtNombreSolicitante.text = "Solicitante : ${postulacion.nombreUsuario}"
            itemRequestTxtNombreEmpleo.text = "Vacante : ${postulacion.nombreVacante}"
            itemRequestTxtEstatus.text = "Estatus : ${postulacion.estatus}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostulacionesViewHolder {
        val binding =
            ItemPostulacionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostulacionesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostulacionesViewHolder, position: Int) {
        val postulacion = getItem(position)
        holder.bind(postulacion)
    }

    object DiffCallback : DiffUtil.ItemCallback<Postulacion>() {
        override fun areItemsTheSame(oldItem: Postulacion, newItem: Postulacion) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Postulacion, newItem: Postulacion) =
            oldItem == newItem
    }
}