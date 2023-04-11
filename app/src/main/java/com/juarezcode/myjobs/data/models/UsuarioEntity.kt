package com.juarezcode.myjobs.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_usuarios")
data class UsuarioEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "apellido") val apellido: String,
    @ColumnInfo(name = "nombre_de_usuario") val nombreDeUsuario: String,
    @ColumnInfo(name = "es_administrador") val esAdministrador: Boolean = false,
    @ColumnInfo(name = "edad") val edad: Int,
    @ColumnInfo(name = "carrera") val carrera: String,
    @ColumnInfo(name = "experiencia_laboral") val experienciaLaboral: String,
    @ColumnInfo(name = "password") val contrasenia: String,
)

fun UsuarioEntity.convertirAUsuarioSesionActual(): UsuarioSesionActual {
    return UsuarioSesionActual(
        id = this.id,
        nombre = this.nombre,
        nombreDeUsuario = this.nombreDeUsuario,
        esAdministrador = this.esAdministrador,
        contrasenia = this.contrasenia
    )
}

fun UsuarioEntity.convertirAUsuario(): Usuario {
    return Usuario(
        id = this.id,
        nombre = this.nombre,
        apellido = this.apellido,
        nombreDeUsuario = this.nombreDeUsuario,
        esAdministrador = this.esAdministrador,
        edad = this.edad,
        carrera = this.carrera,
        experienciaLaboral = this.experienciaLaboral
    )
}