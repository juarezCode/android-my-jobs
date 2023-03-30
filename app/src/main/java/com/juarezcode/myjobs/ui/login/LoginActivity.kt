package com.juarezcode.myjobs.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.juarezcode.myjobs.databinding.ActivityLoginBinding
import com.juarezcode.myjobs.ui.createuser.CreateUserActivity
import com.juarezcode.myjobs.ui.home.HomeAdminActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botonAbrirPantallaHome.setOnClickListener {
            val nombreDeUsuario = binding.inputUsuario.text.toString()
            val contrasenia = binding.inputContrasenia.text.toString()
            viewModel.iniciarSesion(nombreDeUsuario, contrasenia)
        }

        binding.botonAbrirPantallaCrearCuenta.setOnClickListener {
            val intent = Intent(this, CreateUserActivity::class.java)
            startActivity(intent)
        }

        viewModel.loginState.observe(this) { estado ->
            when (estado) {
                LoginState.ERROR -> mostrarMensajeDeError()
                LoginState.EXITO -> abrirPantallaHome()
                LoginState.INICIAL -> Unit
            }
        }
    }

    private fun abrirPantallaHome() {
        val intent = Intent(this, HomeAdminActivity::class.java)
        startActivity(intent)
    }

    private fun mostrarMensajeDeError() {
        Toast.makeText(this, "Usuario o Contrasenia incorrrecta", Toast.LENGTH_LONG).show()
    }
}