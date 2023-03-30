package com.juarezcode.myjobs.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.juarezcode.myjobs.data.repositorio.MainRepository
import kotlinx.coroutines.launch

class LoginViewModel(val context: Application) : AndroidViewModel(context) {
    private val repositorio = MainRepository(context)

    private var _loginState = MutableLiveData<LoginState>(LoginState.Inicial)
    val loginState: LiveData<LoginState> = _loginState

    fun iniciarSesion(nombreDeUsuario: String, contrasenia: String) = viewModelScope.launch {
        val usuarioEncontrado = repositorio.iniciarSesion(nombreDeUsuario, contrasenia)
        if (usuarioEncontrado != null) {
            _loginState.value = LoginState.Exito(usuarioEncontrado)
        } else {
            _loginState.value = LoginState.Error
        }

        _loginState.value = LoginState.Inicial
    }
}