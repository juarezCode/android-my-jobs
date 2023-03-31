package com.juarezcode.myjobs.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.juarezcode.myjobs.data.models.PostulacionEntity

@Dao
interface PostulacionDao {

    @Query("SELECT * FROM tabla_postulaciones")
    suspend fun obtenerTodasLasPostulaciones(): List<PostulacionEntity>

    @Query("SELECT * FROM tabla_postulaciones WHERE usuario_id LIKE :usuarioId")
    suspend fun obtenerTodasLasPostulacionesPorUsuarioId(usuarioId: Int): List<PostulacionEntity>

    @Query("SELECT * FROM tabla_postulaciones WHERE usuario_id LIKE :usuarioId AND vacante_id LIKE :vacanteId")
    suspend fun validarPostulacionPrevia(usuarioId: Int, vacanteId: Int): PostulacionEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarPostulacion(postulacion: PostulacionEntity)
}