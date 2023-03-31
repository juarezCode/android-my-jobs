package com.juarezcode.myjobs.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.juarezcode.myjobs.data.models.Postulacion
import com.juarezcode.myjobs.data.repositorio.MainRepository
import kotlinx.coroutines.launch

class HomeViewModel(val context: Application) : AndroidViewModel(context) {
    private val repositorio = MainRepository(context)

    private var _postulaciones = MutableLiveData<List<Postulacion>>(emptyList())
    val postulaciones: LiveData<List<Postulacion>> = _postulaciones

    private var _misPostulaciones = MutableLiveData<List<Postulacion>>(emptyList())
    val misPostulaciones: LiveData<List<Postulacion>> = _misPostulaciones

    fun obtenerMisPostulaciones(usuarioId: Int) = viewModelScope.launch {
        val postulaciones = repositorio.obtenerPostulacionesPorUsuarioId(usuarioId)
        _misPostulaciones.value = postulaciones
    }

    fun obtenerPostulaciones() = viewModelScope.launch {
        val postulaciones = repositorio.obtenerPostulaciones()
        _postulaciones.value = postulaciones
    }
}