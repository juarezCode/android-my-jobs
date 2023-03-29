package com.juarezcode.myjobs.ui.job

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.juarezcode.myjobs.databinding.ActivityJobsBinding

class JobsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJobsBinding
    private val viewModel: JobsViewModel by viewModels()
    private val jobsAdapter = JobsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJobsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.obtenerVacantes()

        binding.jobsRecyclerVacantes.apply {
            layoutManager =
                LinearLayoutManager(this@JobsActivity, LinearLayoutManager.VERTICAL, false)
            adapter = jobsAdapter
        }

        binding.botonJobsIrAPantallaCrearNuevaVacante.setOnClickListener {
            val intent = Intent(this, CreateJobActivity::class.java)
            startActivity(intent)
        }

        viewModel.vacantes.observe(this) {
            jobsAdapter.submitList(it)
        }
    }
}