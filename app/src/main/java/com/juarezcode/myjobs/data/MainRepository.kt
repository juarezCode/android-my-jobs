package com.juarezcode.myjobs.data

import com.juarezcode.myjobs.ui.home.Vacante

class MainRepository {

    fun obtenerVacantes(): List<Vacante> {
        return listOf(
            Vacante(1, "Profesor", "Profesor de Universidad"),
            Vacante(2, "Maestro", "Maestro de Primaria"),
            Vacante(3, "Plomero", "Plomero"),
            Vacante(4, "Musico de Jazz", "Musico de Jazz"),
        )
    }
}