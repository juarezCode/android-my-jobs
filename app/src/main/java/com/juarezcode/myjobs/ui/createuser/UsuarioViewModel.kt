package com.juarezcode.myjobs.ui.createuser

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.juarezcode.myjobs.data.models.Usuario
import com.juarezcode.myjobs.data.models.UsuarioEntity
import com.juarezcode.myjobs.data.repositorio.UsuarioRepositorio
import com.juarezcode.myjobs.utils.Estatus
import kotlinx.coroutines.launch

class UsuarioViewModel(val context: Application) : AndroidViewModel(context) {
    private val repositorio = UsuarioRepositorio(context)

    private var _crearUsuarioState = MutableLiveData<Estatus>(Estatus.Inicial)
    val crearUsuarioState: LiveData<Estatus> = _crearUsuarioState

    private var _usuario = MutableLiveData<Usuario?>(null)
    val usuario: LiveData<Usuario?> = _usuario

    fun guardarUsuario(usuario: UsuarioEntity) = viewModelScope.launch {
        val crearUsuarioEstatus = repositorio.guardarUsuario(usuario)
        _crearUsuarioState.value = crearUsuarioEstatus
    }

    fun obtenerUsuarioPorId(usuarioId: Int) = viewModelScope.launch {
        _usuario.value = repositorio.obtenerUsuarioPorId(usuarioId)
    }
}