package com.juarezcode.myjobs.ui.job

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.juarezcode.myjobs.databinding.ActivityCreateJobBinding

class CreateJobActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateJobBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateJobBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botonCrearNuevaVacante.setOnClickListener {
            finish()
        }
    }
}