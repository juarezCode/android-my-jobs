package com.juarezcode.myjobs.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.juarezcode.myjobs.data.models.AdminSolicitud
import com.juarezcode.myjobs.data.repositorio.MainRepository

class HomeViewModel(val context: Application) : AndroidViewModel(context) {
    private val repositorio = MainRepository(context)

    private var _solicitudes = MutableLiveData<List<AdminSolicitud>>(emptyList())
    val solicitudes: LiveData<List<AdminSolicitud>> = _solicitudes

    fun obtenerSolicitudes() {
        val solicitudes = repositorio.obtenerSolicitudes()
        _solicitudes.value = solicitudes
    }
}