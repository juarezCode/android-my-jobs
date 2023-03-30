package com.juarezcode.myjobs.ui.login

sealed interface LoginState {
    object INICIAL : LoginState
    object EXITO : LoginState
    object ERROR : LoginState
}