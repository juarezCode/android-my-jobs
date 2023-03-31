package com.juarezcode.myjobs.data.repositorio

import android.content.Context
import com.juarezcode.myjobs.data.local.AppDatabase
import com.juarezcode.myjobs.data.local.PreferenciasLocales
import com.juarezcode.myjobs.data.models.UsuarioSesionActual
import com.juarezcode.myjobs.data.models.convertirAUsuarioSesionActual
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRepository(context: Context) {
    private val usuarioDao = AppDatabase.getInstance(context).usuarioDao()
    private val preferenciasLocales = PreferenciasLocales.getInstance(context)

    suspend fun iniciarSesion(nombreDeUsuario: String, contrasenia: String): UsuarioSesionActual? {
        val usuarioEncontrado =
            withContext(Dispatchers.IO) { usuarioDao.iniciarSesion(nombreDeUsuario, contrasenia) }

        if (usuarioEncontrado != null) {
            preferenciasLocales.guardarUsuarioEnSesion(usuarioEncontrado.convertirAUsuarioSesionActual())
            return usuarioEncontrado.convertirAUsuarioSesionActual()
        } else {
            return null
        }
    }
}