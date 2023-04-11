package com.juarezcode.myjobs.ui.createuser

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.juarezcode.myjobs.data.models.UsuarioEntity
import com.juarezcode.myjobs.databinding.ActivityCrearUsuarioBinding
import com.juarezcode.myjobs.utils.Estatus
import com.juarezcode.myjobs.utils.mostrarToast

class CrearUsuarioActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCrearUsuarioBinding
    private val viewModel: UsuarioViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrearUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botonCrearCuentaDeUsuario.setOnClickListener {
            crearNuevoUsuario()
        }

        observarCrearUsuarioState()
    }

    private fun observarCrearUsuarioState() {
        viewModel.crearUsuarioState.observe(this) { estatus ->
            when (estatus) {
                Estatus.Exito -> {
                    mostrarToast("Usuario creado exitosamente")
                    finish()
                }
                Estatus.NombreDeUsuarioNoDisponible -> mostrarToast("El nombre de usuario no esta disponible")
                else -> Unit
            }
        }
    }

    private fun crearNuevoUsuario() {
        val nombre = binding.inputNombre.text.toString()
        val apellido = binding.inputApellido.text.toString()
        val nombreDeUsuario = binding.inputNombreDeUsuario.text.toString()
        val edad = binding.inputEdad.text.toString()
        val carrera = binding.inputCarrera.text.toString()
        val experiencia = binding.inputExperiencia.text.toString()
        val contrasenia = binding.inputContrasenia.text.toString()

        if (nombre.isEmpty() || apellido.isEmpty() || nombreDeUsuario.isEmpty() || edad.isEmpty() || carrera.isEmpty() || experiencia.isEmpty() || contrasenia.isEmpty()) {
            mostrarToast("Ingresa todos los datos")
        } else {
            val nuevoUsuario = UsuarioEntity(
                nombre = nombre,
                apellido = apellido,
                nombreDeUsuario = nombreDeUsuario,
                contrasenia = contrasenia,
                edad = edad.toInt(),
                carrera = carrera,
                experienciaLaboral = experiencia
            )

            viewModel.guardarUsuario(nuevoUsuario)
        }
    }
}