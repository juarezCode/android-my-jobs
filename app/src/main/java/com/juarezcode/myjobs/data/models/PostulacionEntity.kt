package com.juarezcode.myjobs.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_postulaciones")
data class PostulacionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "vacante_id") val vacanteId: Int,
    @ColumnInfo(name = "usuario_id") val usuarioId: Int,
    @ColumnInfo(name = "estatus") val estatus: String,
    @ColumnInfo(name = "fecha_de_cita") val fechaDeCita: String?,
)

//fun PostulacionEntity.convertirAPostulacion(): Postulacion {
//    return Postulacion(
//        id = this.id,
//        vacanteId = this.vacanteId,
//        usuarioId = this.usuarioId,
//        estatus = this.estatus
//    )
//}