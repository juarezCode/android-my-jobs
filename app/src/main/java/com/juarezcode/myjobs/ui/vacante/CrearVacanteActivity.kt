package com.juarezcode.myjobs.ui.vacante

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.juarezcode.myjobs.data.models.VacanteEntity
import com.juarezcode.myjobs.databinding.ActivityCrearVacanteBinding

class CrearVacanteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCrearVacanteBinding
    private val viewModel: VacantesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrearVacanteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.crearVacanteBotonCrearNuevaVacante.setOnClickListener {
            crearNuevaVacante()
            finish()
        }
    }

    private fun crearNuevaVacante() {
        val nombreDeVacante = binding.crearVacanteInputNombreVacante.text.toString()
        val descripcionDeVacante = binding.crearVacanteInputDescripcionVacante.text.toString()

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