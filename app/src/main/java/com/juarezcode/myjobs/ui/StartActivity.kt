package com.juarezcode.myjobs.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.juarezcode.myjobs.data.local.PreferenciasLocales
import com.juarezcode.myjobs.databinding.ActivityStartBinding
import com.juarezcode.myjobs.ui.home.HomeActivity
import com.juarezcode.myjobs.ui.home.HomeAdminActivity
import com.juarezcode.myjobs.ui.login.LoginActivity

class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding
    private val preferenciasLocales by lazy { PreferenciasLocales.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        validarSiYaExisteSesionActiva()

        binding.botonIniciarSesionComoAdmin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.botonIniciarSesionNormal.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validarSiYaExisteSesionActiva() {
        if (preferenciasLocales.existeUnaSesionActiva()) {
            if (preferenciasLocales.elUsuarioEnSesionEsAdmin()) {
                val intent = Intent(this, HomeAdminActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            } else {
                val intent = Intent(this, HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }
}