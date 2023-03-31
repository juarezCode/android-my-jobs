package com.juarezcode.myjobs.data.repositorio

import android.content.Context
import com.juarezcode.myjobs.data.local.AppDatabase
import com.juarezcode.myjobs.data.local.PreferenciasLocales
import com.juarezcode.myjobs.data.models.*
import com.juarezcode.myjobs.utils.Estatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository(private val context: Context) {
    private val usuarioDao = AppDatabase.getInstance(context).usuarioDao()
    private val vacanteDao = AppDatabase.getInstance(context).vacanteDao()
    private val postulacionDao = AppDatabase.getInstance(context).postulacionDao()
    private val preferenciasLocales = PreferenciasLocales.getInstance(context)

    suspend fun obtenerVacantes(): List<Vacante> {
        val vacantesGuardadas = withContext(Dispatchers.IO) { vacanteDao.obtenerTodasLasVacantes() }
        return vacantesGuardadas.convertirAVacantes()
    }

    suspend fun obtenerPostulacionesPorUsuarioId(usuarioId: Int): List<Postulacion> {
        val misPostulacionesGuardadas =
            withContext(Dispatchers.IO) {
                postulacionDao.obtenerTodasLasPostulacionesPorUsuarioId(usuarioId)
            }
        val misPostulaciones = mutableListOf<Postulacion>()

        misPostulacionesGuardadas.forEach { entity ->
            val usuarioEntity =
                withContext(Dispatchers.IO) { usuarioDao.obtenerUnUsuarioPorId(entity.usuarioId) }
            val vacanteEntity =
                withContext(Dispatchers.IO) { vacanteDao.obtenerUnaVacantePorId(entity.vacanteId) }

            val myPostulacion = Postulacion(
                id = entity.id,
                vacanteId = entity.vacanteId,
                nombreVacante = vacanteEntity.nombre,
                usuarioId = entity.usuarioId,
                nombreUsuario = usuarioEntity.nombreCompleto,
                estatus = entity.estatus
            )

            misPostulaciones.add(myPostulacion)
        }

        return misPostulaciones
    }

    suspend fun obtenerPostulaciones(): List<Postulacion> {
        val postulacionesGuardadas =
            withContext(Dispatchers.IO) { postulacionDao.obtenerTodasLasPostulaciones() }
        val postulaciones = mutableListOf<Postulacion>()

        postulacionesGuardadas.forEach { entity ->
            val usuarioEntity =
                withContext(Dispatchers.IO) { usuarioDao.obtenerUnUsuarioPorId(entity.usuarioId) }
            val vacanteEntity =
                withContext(Dispatchers.IO) { vacanteDao.obtenerUnaVacantePorId(entity.vacanteId) }

            val postulacion = Postulacion(
                id = entity.id,
                vacanteId = entity.vacanteId,
                nombreVacante = vacanteEntity.nombre,
                usuarioId = entity.usuarioId,
                nombreUsuario = usuarioEntity.nombreCompleto,
                estatus = entity.estatus
            )

            postulaciones.add(postulacion)
        }

        return postulaciones
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

    suspend fun guardarUsuario(usuario: UsuarioEntity): Estatus {
        if (nombreDeUsuarioNoDisponible(usuario.nombreDeUsuario)) {
            return Estatus.NombreDeUsuarioNoDisponible
        } else {
            withContext(Dispatchers.IO) { usuarioDao.insertarUsuario(usuario) }
            return Estatus.Exito
        }
    }

    suspend fun nombreDeUsuarioNoDisponible(nombreDeUsuario: String): Boolean {
        val existe = withContext(Dispatchers.IO) {
            usuarioDao.validarNombreDeUsuarioUnico(nombreDeUsuario)
        }
        return existe != null
    }

    suspend fun guardarVacante(vacante: VacanteEntity) = withContext(Dispatchers.IO) {
        vacanteDao.insertarVacante(vacante)
    }

    suspend fun guardarPostulacion(vacante: Vacante): Estatus {
        val usuarioEnSesion = PreferenciasLocales.getInstance(context).obtenerUsuarioEnSesion()

        if (existePostulacionPrevia(usuarioEnSesion.id, vacante.id)) {
            return Estatus.PostulacionPrevia
        } else {
            val postulacion = PostulacionEntity(
                vacanteId = vacante.id,
                usuarioId = usuarioEnSesion.id,
                estatus = "Pendiente"
            )
            withContext(Dispatchers.IO) { postulacionDao.insertarPostulacion(postulacion) }
            return Estatus.Exito
        }
    }

    suspend fun existePostulacionPrevia(usuarioId: Int, vacanteId: Int): Boolean {
        val existe = withContext(Dispatchers.IO) {
            postulacionDao.validarPostulacionPrevia(usuarioId, vacanteId)
        }
        return existe != null
    }
}