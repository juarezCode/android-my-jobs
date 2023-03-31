package com.juarezcode.myjobs.ui.createuser

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.juarezcode.myjobs.data.models.UsuarioEntity
import com.juarezcode.myjobs.data.repositorio.MainRepository
import com.juarezcode.myjobs.utils.Estatus
import kotlinx.coroutines.launch

class UsuarioViewModel(val context: Application) : AndroidViewModel(context) {
    private val repositorio = MainRepository(context)

    private var _crearUsuarioState = MutableLiveData<Estatus>(Estatus.Inicial)
    val crearUsuarioState: LiveData<Estatus> = _crearUsuarioState

    fun guardarUsuario(usuario: UsuarioEntity) = viewModelScope.launch {
        val crearUsuarioEstatus = repositorio.guardarUsuario(usuario)
        _crearUsuarioState.value = crearUsuarioEstatus
    }
}