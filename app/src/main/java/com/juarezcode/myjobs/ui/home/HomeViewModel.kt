package com.juarezcode.myjobs.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.juarezcode.myjobs.data.MainRepository
import com.juarezcode.myjobs.data.models.AdminSolicitud

class HomeViewModel : ViewModel() {
    private val repository = MainRepository()

    private var _solicitudes = MutableLiveData<List<AdminSolicitud>>(emptyList())
    val solicitudes: LiveData<List<AdminSolicitud>> = _solicitudes

    fun obtenerSolicitudes() {
        val solicitudes = repository.obtenerSolicitudes()
        _solicitudes.value = solicitudes
    }
}