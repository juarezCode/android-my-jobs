package com.juarezcode.myjobs.data.models

data class UsuarioSesionActual(
    val id: Int = 0,
    val nombreCompleto: String,
    val nombreDeUsuario: String,
    val esAdministrador: Boolean,
    val contrasenia: String,
)
