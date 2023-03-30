package com.juarezcode.myjobs.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.juarezcode.myjobs.data.MainRepository
import com.juarezcode.myjobs.data.models.AdminSolicitud

class HomeViewModel(val context: Application) : AndroidViewModel(context) {
    private val repository = MainRepository(context)

    private var _solicitudes = MutableLiveData<List<AdminSolicitud>>(emptyList())
    val solicitudes: LiveData<List<AdminSolicitud>> = _solicitudes

    fun obtenerSolicitudes() {
        val solicitudes = repository.obtenerSolicitudes()
        _solicitudes.value = solicitudes
    }
}