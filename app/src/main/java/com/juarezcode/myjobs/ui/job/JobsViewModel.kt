package com.juarezcode.myjobs.ui.job

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.juarezcode.myjobs.data.MainRepository
import com.juarezcode.myjobs.data.models.Vacante

class JobsViewModel : ViewModel() {

    private val repository = MainRepository()

    private var _vacantes = MutableLiveData<List<Vacante>>(emptyList())
    val vacantes: LiveData<List<Vacante>> = _vacantes

    fun obtenerVacantes() {
        val vacantes = repository.obtenerVacantes()
        _vacantes.value = vacantes
    }
}