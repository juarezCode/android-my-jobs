package com.juarezcode.myjobs.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [UsuarioEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room
                    .databaseBuilder(context, AppDatabase::class.java, "app-citas-empleos")
                    .addCallback(insertarAdminCallback())
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private fun generarUsuarioAdministrador(): UsuarioEntity {
            return UsuarioEntity(
                nombreCompleto = "J Roberto Juarez",
                nombreDeUsuario = "admin",
                esAdministrador = true,
                edad = 27,
                carrera = "Ingenieria de Software",
                contrasenia = "admin"
            )
        }

        private fun insertarAdminCallback() = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    CoroutineScope(Dispatchers.IO).launch {
                        val usuarioAdmin = generarUsuarioAdministrador()
                        database.usuarioDao().insertarUsuario(usuarioAdmin)
                    }
                }
            }
        }
    }
}