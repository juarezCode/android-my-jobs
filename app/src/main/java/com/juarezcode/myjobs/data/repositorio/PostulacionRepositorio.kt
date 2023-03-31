package com.juarezcode.myjobs.data.repositorio

import android.content.Context
import com.juarezcode.myjobs.data.local.AppDatabase
import com.juarezcode.myjobs.data.models.Postulacion
import com.juarezcode.myjobs.data.models.PostulacionEntity
import com.juarezcode.myjobs.data.models.Vacante
import com.juarezcode.myjobs.utils.Estatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostulacionRepositorio(context: Context) {
    private val postulacionDao = AppDatabase.getInstance(context).postulacionDao()
    private val usuarioDao = AppDatabase.getInstance(context).usuarioDao()
    private val vacanteDao = AppDatabase.getInstance(context).vacanteDao()

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

    suspend fun guardarPostulacion(vacante: Vacante, usuarioId: Int): Estatus {

        if (existePostulacionPrevia(usuarioId, vacante.id)) {
            return Estatus.PostulacionPrevia
        } else {
            val postulacion = PostulacionEntity(
                vacanteId = vacante.id,
                usuarioId = usuarioId,
                estatus = "Pendiente"
            )
            withContext(Dispatchers.IO) { postulacionDao.insertarPostulacion(postulacion) }
            return Estatus.Exito
        }
    }

    private suspend fun existePostulacionPrevia(usuarioId: Int, vacanteId: Int): Boolean {
        val existe = withContext(Dispatchers.IO) {
            postulacionDao.validarPostulacionPrevia(usuarioId, vacanteId)
        }
        return existe != null
    }
}