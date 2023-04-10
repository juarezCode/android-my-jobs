package com.juarezcode.myjobs.data.models

data class Usuario(
    val id: Int = 0,
    val nombreCompleto: String,
    val nombreDeUsuario: String,
    val esAdministrador: Boolean = false,
    val edad: Int,
    val carrera: String,
)
