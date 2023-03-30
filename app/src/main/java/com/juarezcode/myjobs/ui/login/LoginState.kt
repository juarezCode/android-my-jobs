package com.juarezcode.myjobs.ui.login

import com.juarezcode.myjobs.data.models.UsuarioSesionActual

sealed interface LoginState {
    object Inicial : LoginState
    data class Exito(val usuario: UsuarioSesionActual) : LoginState
    object Error : LoginState
}