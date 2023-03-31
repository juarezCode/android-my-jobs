package com.juarezcode.myjobs.ui.job

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.juarezcode.myjobs.data.models.Vacante
import com.juarezcode.myjobs.data.models.VacanteEntity
import com.juarezcode.myjobs.data.repositorio.MainRepository
import kotlinx.coroutines.launch

class JobsViewModel(val context: Application) : AndroidViewModel(context) {

    private val repositorio = MainRepository(context)

    private var _vacantes = MutableLiveData<List<Vacante>>(emptyList())
    val vacantes: LiveData<List<Vacante>> = _vacantes

    fun obtenerVacantes() = viewModelScope.launch {
        val vacantes = repositorio.obtenerVacantes()
        _vacantes.value = vacantes
    }

    fun guardarVacante(vacante: VacanteEntity) = viewModelScope.launch {
        repositorio.guardarVacante(vacante)
    }
}