package com.juarezcode.myjobs.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.juarezcode.myjobs.data.models.UsuarioEntity

@Dao
interface UsuarioDao {

    @Query("SELECT * FROM tabla_usuarios WHERE nombre_de_usuario LIKE :nombreDeUsuario AND password LIKE :contrasenia LIMIT 1")
    suspend fun iniciarSesion(nombreDeUsuario: String, contrasenia: String): UsuarioEntity?

    @Query("SELECT * FROM tabla_usuarios")
    fun obtenerTodosLosUsuarios(): List<UsuarioEntity>

    @Query("SELECT * FROM tabla_usuarios WHERE id LIKE :usuarioId LIMIT 1")
    suspend fun obtenerUnUsuarioPorId(usuarioId: Int): UsuarioEntity?

    @Query("SELECT * FROM tabla_usuarios WHERE nombre_de_usuario LIKE :nombreDeUsuario LIMIT 1")
    suspend fun validarNombreDeUsuarioUnico(nombreDeUsuario: String): UsuarioEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertarUsuario(usuario: UsuarioEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertarMuchosUsuarios(usuarios: List<UsuarioEntity>)
}