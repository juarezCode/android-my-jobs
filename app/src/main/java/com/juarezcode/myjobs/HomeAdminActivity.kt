package com.juarezcode.myjobs

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.juarezcode.myjobs.databinding.ActivityHomeAdminBinding

class HomeAdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botonIrAPantallaCrearNuevaVacante.setOnClickListener {
            val intent = Intent(this, CreateJobActivity::class.java)
            startActivity(intent)
        }
    }
}