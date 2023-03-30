package com.juarezcode.myjobs.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UsuarioDao {

    @Query("SELECT * FROM tabla_usuarios WHERE nombre_de_usuario LIKE :nombreDeUsuario AND password LIKE :contrasenia LIMIT 1")
    suspend fun iniciarSesion(nombreDeUsuario: String, contrasenia: String): UsuarioEntity?

    @Query("SELECT * FROM tabla_usuarios")
    fun obtenerTodosLosUsuarios(): List<UsuarioEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertarUsuario(usuario: UsuarioEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertarMuchosUsuarios(usuarios: List<UsuarioEntity>)
}