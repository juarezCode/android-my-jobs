package com.juarezcode.myjobs.data.repositorio

import android.content.Context
import com.juarezcode.myjobs.data.local.AppDatabase
import com.juarezcode.myjobs.data.models.Vacante
import com.juarezcode.myjobs.data.models.VacanteEntity
import com.juarezcode.myjobs.data.models.convertirAVacantes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VacanteRepositorio(context: Context) {
    private val vacanteDao = AppDatabase.getInstance(context).vacanteDao()

    suspend fun obtenerVacantes(): List<Vacante> {
        val vacantesGuardadas = withContext(Dispatchers.IO) { vacanteDao.obtenerTodasLasVacantes() }
        return vacantesGuardadas.convertirAVacantes()
    }

    suspend fun guardarVacante(vacante: VacanteEntity) = withContext(Dispatchers.IO) {
        vacanteDao.insertarVacante(vacante)
    }
}