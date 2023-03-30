package com.juarezcode.myjobs.ui.job

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.juarezcode.myjobs.data.models.Vacante
import com.juarezcode.myjobs.data.repositorio.MainRepository

class JobsViewModel(val context: Application) : AndroidViewModel(context) {

    private val repository = MainRepository(context)

    private var _vacantes = MutableLiveData<List<Vacante>>(emptyList())
    val vacantes: LiveData<List<Vacante>> = _vacantes

    fun obtenerVacantes() {
        val vacantes = repository.obtenerVacantes()
        _vacantes.value = vacantes
    }
}