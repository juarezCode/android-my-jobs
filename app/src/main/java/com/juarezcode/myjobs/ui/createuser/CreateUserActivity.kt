package com.juarezcode.myjobs.ui.createuser

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.juarezcode.myjobs.data.models.UsuarioEntity
import com.juarezcode.myjobs.databinding.ActivityCreateUserBinding
import com.juarezcode.myjobs.utils.Estatus
import com.juarezcode.myjobs.utils.mostrarToast

class CreateUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateUserBinding
    private val viewModel: UsuarioViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botonCrearCuentaDeUsuario.setOnClickListener {
            crearNuevoUsuario()
        }

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
        val nombreCompleto = binding.inputNombreCompleto.text.toString()
        val nombreDeUsuario = binding.inputNombreDeUsuario.text.toString()
        val edad = binding.inputEdad.text.toString()
        val carrera = binding.inputCarrera.text.toString()
        val contrasenia = binding.inputContrasenia.text.toString()

        if (
            nombreCompleto.isEmpty() ||
            nombreDeUsuario.isEmpty() ||
            edad.isEmpty() ||
            carrera.isEmpty() ||
            contrasenia.isEmpty()
        ) {
            mostrarToast("Ingresa todos los datos")
        } else {
            val nuevoUsuario = UsuarioEntity(
                nombreCompleto = nombreCompleto,
                nombreDeUsuario = nombreDeUsuario,
                contrasenia = contrasenia,
                edad = edad.toInt(),
                carrera = carrera,
            )

            viewModel.guardarUsuario(nuevoUsuario)
        }
    }
}