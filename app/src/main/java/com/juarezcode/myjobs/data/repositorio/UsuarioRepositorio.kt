package com.juarezcode.myjobs.data.repositorio

import android.content.Context
import com.juarezcode.myjobs.data.local.AppDatabase
import com.juarezcode.myjobs.data.models.Usuario
import com.juarezcode.myjobs.data.models.UsuarioEntity
import com.juarezcode.myjobs.data.models.convertirAUsuario
import com.juarezcode.myjobs.utils.Estatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UsuarioRepositorio(context: Context) {
    private val usuarioDao = AppDatabase.getInstance(context).usuarioDao()

    suspend fun guardarUsuario(usuario: UsuarioEntity): Estatus {
        if (nombreDeUsuarioNoDisponible(usuario.nombreDeUsuario)) {
            return Estatus.NombreDeUsuarioNoDisponible
        } else {
            withContext(Dispatchers.IO) { usuarioDao.insertarUsuario(usuario) }
            return Estatus.Exito
        }
    }

    private suspend fun nombreDeUsuarioNoDisponible(nombreDeUsuario: String): Boolean {
        val existe = withContext(Dispatchers.IO) {
            usuarioDao.validarNombreDeUsuarioUnico(nombreDeUsuario)
        }
        return existe != null
    }

    suspend fun obtenerUsuarioPorId(usuarioId: Int): Usuario? {
        val usuarioEntity =
            withContext(Dispatchers.IO) { usuarioDao.obtenerUnUsuarioPorId(usuarioId) }
        return usuarioEntity?.convertirAUsuario()
    }
}