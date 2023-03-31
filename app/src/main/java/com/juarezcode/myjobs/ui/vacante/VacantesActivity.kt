package com.juarezcode.myjobs.ui.vacante

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.juarezcode.myjobs.data.local.PreferenciasLocales
import com.juarezcode.myjobs.data.models.Vacante
import com.juarezcode.myjobs.databinding.ActivityVacantesBinding
import com.juarezcode.myjobs.utils.Estatus
import com.juarezcode.myjobs.utils.mostrarToast

class VacantesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVacantesBinding
    private val viewModel: VacantesViewModel by viewModels()
    private val preferenciasLocales = PreferenciasLocales.getInstance(this)
    private val usuarioEnSesion by lazy { preferenciasLocales.obtenerUsuarioEnSesion() }
    private val vacantesAdapter = VacantesAdapter(::postular)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVacantesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setearDatos()

        binding.vacantesRecyclerVacantes.apply {
            layoutManager =
                LinearLayoutManager(this@VacantesActivity, LinearLayoutManager.VERTICAL, false)
            adapter = vacantesAdapter
        }

        binding.vacantesBotonIrAPantallaCrearNuevaVacante.setOnClickListener {
            val intent = Intent(this, CrearVacanteActivity::class.java)
            startActivity(intent)
        }

        viewModel.vacantes.observe(this) {
            vacantesAdapter.submitList(it)
        }

        viewModel.postulacionState.observe(this) { estatus ->
            when (estatus) {
                Estatus.Exito -> {
                    mostrarToast("Te has postulado exitosamente", Toast.LENGTH_LONG)
                    finish()
                }
                Estatus.PostulacionPrevia -> {
                    mostrarToast("Ya te has postulado previamente", Toast.LENGTH_LONG)
                }
                else -> Unit
            }
        }
    }

    private fun postular(vacante: Vacante) {
        viewModel.postular(vacante)
    }

    private fun setearDatos() {
        if (!usuarioEnSesion.esAdministrador) {
            binding.vacantesBotonIrAPantallaCrearNuevaVacante.isVisible = false
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.obtenerVacantes()
    }
}