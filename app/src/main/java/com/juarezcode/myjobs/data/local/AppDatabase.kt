package com.juarezcode.myjobs.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.juarezcode.myjobs.data.models.PostulacionEntity
import com.juarezcode.myjobs.data.models.UsuarioEntity
import com.juarezcode.myjobs.data.models.VacanteEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [UsuarioEntity::class, VacanteEntity::class, PostulacionEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao
    abstract fun vacanteDao(): VacanteDao
    abstract fun postulacionDao(): PostulacionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room
                    .databaseBuilder(context, AppDatabase::class.java, "app-citas-empleos")
                    .addCallback(insertarDatosPorDefaultCallback())
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private fun insertarDatosPorDefaultCallback() = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    CoroutineScope(Dispatchers.IO).launch {
                        val usuarios = ProveedorDeDatos.crearUsuariosPorDefault()
                        database.usuarioDao().insertarMuchosUsuarios(usuarios)
                        val vacantes = ProveedorDeDatos.crearVacantesPorDefault()
                        database.vacanteDao().insertarMuchasVacantes(vacantes)
                    }
                }
            }
        }


    }
}