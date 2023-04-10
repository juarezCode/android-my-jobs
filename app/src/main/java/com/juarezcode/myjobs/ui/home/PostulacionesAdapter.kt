package com.juarezcode.myjobs.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.juarezcode.myjobs.data.local.PreferenciasLocales
import com.juarezcode.myjobs.data.models.Postulacion
import com.juarezcode.myjobs.databinding.ItemPostulacionBinding

class PostulacionesAdapter(
    private val onClickVerDetalle: (Postulacion) -> Unit
) : ListAdapter<Postulacion, PostulacionesAdapter.PostulacionesViewHolder>(DiffCallback) {

    class PostulacionesViewHolder(private val binding: ItemPostulacionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val usuarioEnSesion =
            PreferenciasLocales.getInstance(binding.root.context).obtenerUsuarioEnSesion()

        fun bind(postulacion: Postulacion, onClickVerDetalle: (Postulacion) -> Unit) =
            with(binding) {
                if (usuarioEnSesion.esAdministrador) {
                    itemPostulacionBtnVerDetalle.isVisible = true
                    itemPostulacionBtnVerDetalle.setOnClickListener {
                        onClickVerDetalle(postulacion)
                    }
                    itemPostulacionTxtNombreSolicitante.isVisible = true
                    itemPostulacionTxtNombreSolicitante.text =
                        "Solicitante : ${postulacion.nombreUsuario}"
                } else {
                    itemPostulacionBtnVerDetalle.isVisible = false
                    itemPostulacionTxtNombreSolicitante.isVisible = false
                }

                itemPostulacionTxtFechaDeCita.text =
                    "Fecha de cita : ${postulacion.fechaDeCita.orEmpty()}"
                itemPostulacionTxtNombreEmpleo.text = "Vacante : ${postulacion.nombreVacante}"
                itemPostulacionTxtEstatus.text = "Estatus : ${postulacion.estatus}"
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostulacionesViewHolder {
        val binding =
            ItemPostulacionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostulacionesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostulacionesViewHolder, position: Int) {
        val postulacion = getItem(position)
        holder.bind(postulacion, onClickVerDetalle)
    }

    object DiffCallback : DiffUtil.ItemCallback<Postulacion>() {
        override fun areItemsTheSame(oldItem: Postulacion, newItem: Postulacion) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Postulacion, newItem: Postulacion) =
            oldItem == newItem
    }
}