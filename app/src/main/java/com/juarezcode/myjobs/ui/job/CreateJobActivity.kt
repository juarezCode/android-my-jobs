package com.juarezcode.myjobs.ui.job

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.juarezcode.myjobs.data.models.VacanteEntity
import com.juarezcode.myjobs.databinding.ActivityCreateJobBinding

class CreateJobActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateJobBinding
    private val viewModel: JobsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateJobBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.createJobBotonCrearNuevaVacante.setOnClickListener {
            crearNuevaVacante()
            finish()
        }
    }

    private fun crearNuevaVacante() {
        val nombreDeVacante = binding.createJobInputNombreVacante.text.toString()
        val descripcionDeVacante = binding.createJobInputDescripcionVacante.text.toString()

        if (nombreDeVacante.isEmpty() || descripcionDeVacante.isEmpty()) {
            Toast.makeText(this, "Ingresa todos los datos", Toast.LENGTH_SHORT).show()
        }

        val nuevaVacante = VacanteEntity(
            nombre = nombreDeVacante,
            descripcion = descripcionDeVacante
        )

        viewModel.guardarVacante(nuevaVacante)
        Toast.makeText(this, "Vacante creada exitosamente", Toast.LENGTH_SHORT).show()
    }
}