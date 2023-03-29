package com.juarezcode.myjobs.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.juarezcode.myjobs.databinding.ActivityHomeAdminBinding
import com.juarezcode.myjobs.ui.job.JobsActivity

class HomeAdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeAdminBinding
    private val viewModel: HomeViewModel by viewModels()
    private val requestsAdapter = RequestsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.obtenerSolicitudes()

        binding.homeAdminRecyclerSolicitudes.apply {
            layoutManager =
                LinearLayoutManager(this@HomeAdminActivity, LinearLayoutManager.VERTICAL, false)
            adapter = requestsAdapter
        }

        binding.homeBotonIrAPantallaVacantes.setOnClickListener {
            val intent = Intent(this, JobsActivity::class.java)
            startActivity(intent)
        }

        viewModel.solicitudes.observe(this) {
            requestsAdapter.submitList(it)
        }
    }
}