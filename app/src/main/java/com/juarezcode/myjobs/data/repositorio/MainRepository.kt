package com.juarezcode.myjobs.data.repositorio

import android.content.Context
import com.juarezcode.myjobs.data.local.AppDatabase
import com.juarezcode.myjobs.data.local.PreferenciasLocales
import com.juarezcode.myjobs.data.local.UsuarioEntity
import com.juarezcode.myjobs.data.local.convertirAUsuarioSesionActual
import com.juarezcode.myjobs.data.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository(context: Context) {
    private val usuarioDao = AppDatabase.getInstance(context).usuarioDao()
    private val preferenciasLocales = PreferenciasLocales.getInstance(context)

    suspend fun obtenerVacantes(): List<Vacante> {
        val vacantesGuardadas = withContext(Dispatchers.IO) { usuarioDao.obtenerTodasLasVacantes() }
        return vacantesGuardadas.convertirAVacantes()
    }

    fun obtenerSolicitudes(): List<AdminSolicitud> {
        return emptyList()
//        return listOf(
//            AdminSolicitud(1, "Jose", "Profesor", "Pendiente"),
//            AdminSolicitud(2, "Juan", "Maestro", "Pendiente"),
//            AdminSolicitud(3, "Pedro", "Plomero", "Pendiente"),
//            AdminSolicitud(4, "Luis", "Musico de Jazz", "Pendiente"),
//        )
    }

    suspend fun iniciarSesion(nombreDeUsuario: String, contrasenia: String): UsuarioSesionActual? {
        val usuarioEncontrado = withContext(Dispatchers.IO) {
            usuarioDao.iniciarSesion(nombreDeUsuario, contrasenia)
        }

        if (usuarioEncontrado != null) {
            preferenciasLocales.guardarUsuarioEnSesion(usuarioEncontrado.convertirAUsuarioSesionActual())
            return usuarioEncontrado.convertirAUsuarioSesionActual()
        } else {
            return null
        }
    }

    suspend fun guardarUsuario(usuario: UsuarioEntity) = withContext(Dispatchers.IO) {
        usuarioDao.insertarUsuario(usuario)
    }

    suspend fun guardarVacante(vacante: VacanteEntity) = withContext(Dispatchers.IO) {
        usuarioDao.insertarVacante(vacante)
    }
}