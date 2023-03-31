package com.juarezcode.myjobs.ui.vacante

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.juarezcode.myjobs.data.models.Vacante
import com.juarezcode.myjobs.data.models.VacanteEntity
import com.juarezcode.myjobs.data.repositorio.MainRepository
import com.juarezcode.myjobs.utils.Estatus
import kotlinx.coroutines.launch

class VacantesViewModel(val context: Application) : AndroidViewModel(context) {

    private val repositorio = MainRepository(context)

    private var _vacantes = MutableLiveData<List<Vacante>>(emptyList())
    val vacantes: LiveData<List<Vacante>> = _vacantes

    private var _postulacionState = MutableLiveData<Estatus>(Estatus.Inicial)
    val postulacionState: LiveData<Estatus> = _postulacionState

    fun obtenerVacantes() = viewModelScope.launch {
        val vacantes = repositorio.obtenerVacantes()
        _vacantes.value = vacantes
    }

    fun guardarVacante(vacante: VacanteEntity) = viewModelScope.launch {
        repositorio.guardarVacante(vacante)
    }

    fun postular(vacante: Vacante) = viewModelScope.launch {
        val estatusPostulacion = repositorio.guardarPostulacion(vacante)
        _postulacionState.value = estatusPostulacion
    }
}