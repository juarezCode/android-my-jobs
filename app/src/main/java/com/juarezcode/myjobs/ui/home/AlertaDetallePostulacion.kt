package com.juarezcode.myjobs.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.juarezcode.myjobs.R
import com.juarezcode.myjobs.databinding.AlertaDetallePostulacionBinding
import com.juarezcode.myjobs.ui.createuser.UsuarioViewModel

class AlertaDetallePostulacion : DialogFragment() {

    private var _binding: AlertaDetallePostulacionBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UsuarioViewModel by viewModels()
    private var clickAsignarFecha: () -> Unit = {}
    private var clickRechazar: () -> Unit = {}

    companion object {
        const val TAG = "AlertaDetallePostulacion"
        private const val ARG_NOMBRE_VACANTE = "ARG_NOMBRE_VACANTE"
        private const val ARG_USUARIO_ID = "ARG_USUARIO_ID"

        fun newInstance(
            nombreVacante: String,
            usuarioId: Int,
        ): AlertaDetallePostulacion {
            return AlertaDetallePostulacion().apply {
                arguments = Bundle().apply {
                    putString(ARG_NOMBRE_VACANTE, nombreVacante)
                    putInt(ARG_USUARIO_ID, usuarioId)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.DialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AlertaDetallePostulacionBinding.inflate(inflater, container, false)
        isCancelable = false
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observarUsuario()

        arguments?.let {
            it.getString(ARG_NOMBRE_VACANTE)?.let { nombreVacante ->
                binding.detallePostulacionTxtNombreVacante.text = nombreVacante
            }
            it.getInt(ARG_USUARIO_ID).let { usuarioId ->
                viewModel.obtenerUsuarioPorId(usuarioId)
            }
        }

        binding.detallePostulacionBtnAsignarFecha.setOnClickListener {
            dismiss()
            this.clickAsignarFecha()
        }

        binding.detallePostulacionBtnRechazar.setOnClickListener {
            dismiss()
            this.clickRechazar()
        }
        binding.detallePostulacionBtnCerrarAlerta.setOnClickListener {
            dismiss()
        }
    }

    private fun observarUsuario() {
        viewModel.usuario.observe(this) {
            it?.let { usuario ->
                binding.detallePostulacionTxtDetallePostulante.text =
                    "Datos del postulante:\n\n" +
                            "nombre: ${usuario.nombre} ${usuario.apellido}\n\n" +
                            "edad: ${usuario.edad}\n\n" +
                            "carrera: ${usuario.carrera}\n\n" +
                            "experiencia laboral: ${usuario.experienciaLaboral}"
            }
        }
    }

    fun setClickAsignarFecha(clickAsignarFecha: () -> Unit) {
        this.clickAsignarFecha = clickAsignarFecha
    }

    fun setClickRechazar(clickRechazar: () -> Unit) {
        this.clickRechazar = clickRechazar
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}