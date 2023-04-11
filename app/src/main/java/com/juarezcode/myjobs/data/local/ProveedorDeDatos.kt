package com.juarezcode.myjobs.data.local

import com.juarezcode.myjobs.data.models.UsuarioEntity
import com.juarezcode.myjobs.data.models.VacanteEntity

object ProveedorDeDatos {

    fun crearUsuariosPorDefault(): List<UsuarioEntity> {
        return listOf(
            UsuarioEntity(
                nombre = "Roberto",
                apellido = "Juarez",
                nombreDeUsuario = "admin",
                esAdministrador = true,
                edad = 27,
                carrera = "Ingenieria de Software",
                experienciaLaboral = "ninguna",
                contrasenia = "admin"
            ),
            UsuarioEntity(
                nombre = "Jose Roberto",
                apellido = "Juarez Apale",
                nombreDeUsuario = "normal",
                esAdministrador = false,
                edad = 27,
                carrera = "Ingenieria de Software",
                experienciaLaboral = "Trabaje en facebook por 6 meses",
                contrasenia = "normal"
            ),
            UsuarioEntity(
                nombre = "Jose",
                apellido = "Juarez",
                nombreDeUsuario = "normal2",
                esAdministrador = false,
                edad = 27,
                carrera = "Ingenieria de Software",
                experienciaLaboral = "ninguna",
                contrasenia = "normal2"
            )
        )
    }

    fun crearVacantesPorDefault(): List<VacanteEntity> {
        return listOf(
            VacanteEntity(
                nombre = "maestro de ingles",
                descripcion = "maestro de ingles experto"
            ),
            VacanteEntity(
                nombre = "maestro de matematicas",
                descripcion = "maestro de matematicas experto"
            )
        )
    }
}