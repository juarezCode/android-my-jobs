package com.juarezcode.myjobs.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.juarezcode.myjobs.data.models.AdminSolicitud
import com.juarezcode.myjobs.databinding.ItemRequestBinding

class RequestsAdapter :
    ListAdapter<AdminSolicitud, RequestsAdapter.RequestsViewHolder>(DiffCallback) {

    class RequestsViewHolder(private val binding: ItemRequestBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(solicitud: AdminSolicitud) = with(binding) {
            itemRequestTxtNombreSolicitante.text = "Solicitante : ${solicitud.nombreUsuario}"
            itemRequestTxtNombreEmpleo.text = "Vacante : ${solicitud.nombreEmpleo}"
            itemRequestTxtEstatus.text = "Estatus : ${solicitud.estatus}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestsViewHolder {
        val binding = ItemRequestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RequestsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RequestsViewHolder, position: Int) {
        val solicitud = getItem(position)
        holder.bind(solicitud)
    }

    object DiffCallback : DiffUtil.ItemCallback<AdminSolicitud>() {
        override fun areItemsTheSame(oldItem: AdminSolicitud, newItem: AdminSolicitud) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: AdminSolicitud, newItem: AdminSolicitud) =
            oldItem == newItem
    }
}