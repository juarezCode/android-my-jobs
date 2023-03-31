package com.juarezcode.myjobs.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.juarezcode.myjobs.data.local.PreferenciasLocales
import com.juarezcode.myjobs.databinding.ActivityHomeBinding
import com.juarezcode.myjobs.ui.vacante.VacantesActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val preferenciasLocales = PreferenciasLocales.getInstance(this)
    private val viewModel: HomeViewModel by viewModels()
    private val postulacionesAdapter = PostulacionesAdapter()
    private val usuarioEnSesion by lazy { preferenciasLocales.obtenerUsuarioEnSesion() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setearDatos()

        binding.homeRecyclerPostulaciones.apply {
            layoutManager =
                LinearLayoutManager(this@HomeActivity, LinearLayoutManager.VERTICAL, false)
            adapter = postulacionesAdapter
        }

        binding.homeBotonIrAPantallaVacantes.setOnClickListener {
            val intent = Intent(this, VacantesActivity::class.java)
            startActivity(intent)
        }

        viewModel.misPostulaciones.observe(this) { postulaciones ->
            postulacionesAdapter.submitList(postulaciones)
        }
    }

    private fun setearDatos() {
        binding.hometTxtUsuarioNombre.text = usuarioEnSesion.nombreCompleto
    }

    override fun onResume() {
        super.onResume()
        viewModel.obtenerMisPostulaciones(usuarioEnSesion.id)
    }
}