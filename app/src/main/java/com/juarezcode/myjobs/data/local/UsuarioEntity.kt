package com.juarezcode.myjobs.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_usuarios")
data class UsuarioEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "nombre_completo") val nombreCompleto: String,
    @ColumnInfo(name = "nombre_de_usuario") val nombreDeUsuario: String,
    @ColumnInfo(name = "es_administrador") val esAdministrador: Boolean,
    @ColumnInfo(name = "edad") val edad: Int,
    @ColumnInfo(name = "carrera") val carrera: String,
    @ColumnInfo(name = "password") val contrasenia: String,
)