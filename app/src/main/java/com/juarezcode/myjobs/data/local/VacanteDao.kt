package com.juarezcode.myjobs.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.juarezcode.myjobs.data.models.VacanteEntity

@Dao
interface VacanteDao {

    @Query("SELECT * FROM tabla_vacantes")
    suspend fun obtenerTodasLasVacantes(): List<VacanteEntity>

    @Query("SELECT * FROM tabla_vacantes WHERE id LIKE :vacanteId LIMIT 1")
    suspend fun obtenerUnaVacantePorId(vacanteId: Int): VacanteEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarVacante(vacante: VacanteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertarMuchasVacantes(vacantes: List<VacanteEntity>)
}