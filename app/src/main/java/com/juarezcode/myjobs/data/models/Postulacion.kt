package com.juarezcode.myjobs.data.models

data class Postulacion(
    val id: Int,
    val vacanteId: Int,
    val nombreVacante: String,
    val usuarioId: Int,
    val nombreUsuario: String,
    val estatus: String
)
