package com.juarezcode.myjobs.ui.createuser

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.juarezcode.myjobs.data.local.UsuarioEntity
import com.juarezcode.myjobs.databinding.ActivityCreateUserBinding

class CreateUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateUserBinding
    private val viewModel: UsuarioViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botonCrearCuentaDeUsuario.setOnClickListener {
            crearNuevoUsuario()
            finish()
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
            Toast.makeText(this, "Ingresa todos los datos", Toast.LENGTH_SHORT).show()
        }

        val nuevoUsuario = UsuarioEntity(
            nombreCompleto = nombreCompleto,
            nombreDeUsuario = nombreDeUsuario,
            contrasenia = contrasenia,
            edad = edad.toInt(),
            carrera = carrera,
        )

        viewModel.guardarUsuario(nuevoUsuario)
        Toast.makeText(this, "Usuario creado exitosamente", Toast.LENGTH_SHORT).show()

    }
}