package com.juarezcode.myjobs.data.local

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.juarezcode.myjobs.common.Constantes
import com.juarezcode.myjobs.data.models.UsuarioSesionActual


class PreferenciasLocales(context: Context) {

    private var preferences: SharedPreferences = context.getSharedPreferences(
        "preferencias_empleos",
        Context.MODE_PRIVATE
    )

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    companion object {
        private const val DEFAULT_INT = -1
        private const val DEFAULT_BOOLEAN = false
        private lateinit var INSTANCE: PreferenciasLocales

        @JvmStatic
        fun getInstance(context: Context): PreferenciasLocales {
            if (!::INSTANCE.isInitialized) INSTANCE = PreferenciasLocales(context)
            return INSTANCE
        }
    }

    fun getInt(key: String): Int? = preferences.getInt(key, DEFAULT_INT).let { value ->
        if (value == DEFAULT_INT) return null
        return value
    }

    private fun getString(key: String): String? = preferences.getString(key, null)

    private fun getBoolean(key: String): Boolean = preferences.getBoolean(key, DEFAULT_BOOLEAN)

    private fun put(key: String, value: String?) = preferences.edit { it.putString(key, value) }

    private fun put(key: String, value: Int) = preferences.edit { it.putInt(key, value) }

    private fun put(key: String, value: Boolean) = preferences.edit { it.putBoolean(key, value) }

    fun clear() = preferences.edit { it.clear() }

    fun obtenerUsuarioEnSesion(): UsuarioSesionActual {
        val usuarioString = getString(Constantes.PREF_KEY_USUARIO_EN_SESION)!!
        return Gson().fromJson(usuarioString, UsuarioSesionActual::class.java)
    }

    fun guardarUsuarioEnSesion(usuario: UsuarioSesionActual) {
        val usuarioString = Gson().toJson(usuario)
        put(Constantes.PREF_KEY_USUARIO_EN_SESION, usuarioString)
    }

}