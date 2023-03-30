package com.juarezcode.myjobs.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.juarezcode.myjobs.data.local.PreferenciasLocales
import com.juarezcode.myjobs.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val preferenciasLocales = PreferenciasLocales.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        obtenerUsuarioActual()
    }


    fun obtenerUsuarioActual() {
        val usuarioEnSesion = preferenciasLocales.obtenerUsuarioEnSesion()
        binding.hometTxtUsuarioNombre.text = usuarioEnSesion.nombreCompleto
    }
}