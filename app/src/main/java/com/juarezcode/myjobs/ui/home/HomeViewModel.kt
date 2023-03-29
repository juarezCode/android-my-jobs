package com.juarezcode.myjobs.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.juarezcode.myjobs.data.MainRepository

class HomeViewModel : ViewModel() {
    private val repository = MainRepository()

    private var _vacantes = MutableLiveData<List<Vacante>>(emptyList())
    val vacantes: LiveData<List<Vacante>> = _vacantes

    fun obtenerVacantes() {
        val vacantes = repository.obtenerVacantes()
        _vacantes.value = vacantes
    }
}

data class Vacante(
    val id: Int,
    val nombre: String,
    val descripcion: String
)