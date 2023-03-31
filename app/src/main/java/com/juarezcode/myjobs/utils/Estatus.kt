package com.juarezcode.myjobs.utils

sealed interface Estatus {
    object Inicial : Estatus
    object Exito : Estatus
    object Error : Estatus
    object PostulacionPrevia : Estatus
    object NombreDeUsuarioNoDisponible : Estatus
}