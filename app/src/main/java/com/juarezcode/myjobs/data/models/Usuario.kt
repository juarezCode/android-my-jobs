package com.juarezcode.myjobs.data.models

data class Usuario(
    val id: Int = 0,
    val nombre: String,
    val apellido: String,
    val nombreDeUsuario: String,
    val esAdministrador: Boolean = false,
    val edad: Int,
    val carrera: String,
    val experienciaLaboral: String
)
