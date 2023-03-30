package com.juarezcode.myjobs.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.juarezcode.myjobs.data.MainRepository
import kotlinx.coroutines.launch

class LoginViewModel(val context: Application) : AndroidViewModel(context) {
    private val repositorio = MainRepository(context)

    private var _loginState = MutableLiveData<LoginState>(LoginState.INICIAL)
    val loginState: LiveData<LoginState> = _loginState

    fun iniciarSesion(nombreDeUsuario: String, contrasenia: String) = viewModelScope.launch {
        val usuario = repositorio.iniciarSesion(nombreDeUsuario, contrasenia)
        if (usuario == null) _loginState.value = LoginState.ERROR
        else _loginState.value = LoginState.EXITO

        _loginState.value = LoginState.INICIAL
    }
}