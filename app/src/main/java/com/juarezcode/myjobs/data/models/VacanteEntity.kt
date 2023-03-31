package com.juarezcode.myjobs.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_vacantes")
data class VacanteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "descripcion") val descripcion: String
)

fun VacanteEntity.convertirAVacante(): Vacante {
    return Vacante(
        id = this.id,
        nombre = this.nombre,
        descripcion = this.descripcion
    )
}

fun List<VacanteEntity>.convertirAVacantes(): List<Vacante> {
    return this.map { vacanteEntity -> vacanteEntity.convertirAVacante() }
}
