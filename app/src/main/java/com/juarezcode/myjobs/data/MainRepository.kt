package com.juarezcode.myjobs.data

import com.juarezcode.myjobs.data.models.AdminSolicitud
import com.juarezcode.myjobs.data.models.Vacante

class MainRepository {

    fun obtenerVacantes(): List<Vacante> {
        return listOf(
            Vacante(1, "Profesor", "Profesor de Universidad"),
            Vacante(2, "Maestro", "Maestro de Primaria"),
            Vacante(3, "Plomero", "Plomero"),
            Vacante(4, "Musico de Jazz", "Musico de Jazz"),
        )
    }

    fun obtenerSolicitudes(): List<AdminSolicitud> {
        return listOf(
            AdminSolicitud(1, "Jose", "Profesor", "Pendiente"),
            AdminSolicitud(2, "Juan", "Maestro", "Pendiente"),
            AdminSolicitud(3, "Pedro", "Plomero", "Pendiente"),
            AdminSolicitud(4, "Luis", "Musico de Jazz", "Pendiente"),
        )
    }
}