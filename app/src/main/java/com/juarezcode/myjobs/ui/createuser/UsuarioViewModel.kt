package com.juarezcode.myjobs.ui.createuser

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.juarezcode.myjobs.data.local.UsuarioEntity
import com.juarezcode.myjobs.data.repositorio.MainRepository
import kotlinx.coroutines.launch

class UsuarioViewModel(val context: Application) : AndroidViewModel(context) {
    private val repositorio = MainRepository(context)

    fun guardarUsuario(usuario: UsuarioEntity) = viewModelScope.launch {
        repositorio.guardarUsuario(usuario)
    }
}