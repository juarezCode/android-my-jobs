package com.juarezcode.myjobs.data

import android.content.Context
import com.juarezcode.myjobs.data.local.AppDatabase
import com.juarezcode.myjobs.data.local.UsuarioEntity
import com.juarezcode.myjobs.data.models.AdminSolicitud
import com.juarezcode.myjobs.data.models.Vacante
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository(context: Context) {
    private val usuarioDao = AppDatabase.getInstance(context).usuarioDao()

    fun obtenerVacantes(): List<Vacante> {
        return listOf(
            Vacante(1, "Profesor", "Profesor de Universidad"),
            Vacante(2, "Maestro", "Maestro de Primaria"),
            Vacante(3, "Plomero", "Plomero"),
            Vacante(4, "Musico de Jazz", "Musico de Jazz"),
        )
    }

    fun obtenerSolicitudes(): List<AdminSolicitud> {
        return listOf(
            AdminSolicitud(1, "Jose", "Profesor", "Pendiente"),
            AdminSolicitud(2, "Juan", "Maestro", "Pendiente"),
            AdminSolicitud(3, "Pedro", "Plomero", "Pendiente"),
            AdminSolicitud(4, "Luis", "Musico de Jazz", "Pendiente"),
        )
    }

    suspend fun iniciarSesion(nombreDeUsuario: String, contrasenia: String): UsuarioEntity? {
        return withContext(Dispatchers.IO) {
            usuarioDao.iniciarSesion(nombreDeUsuario, contrasenia)
        }
    }
}