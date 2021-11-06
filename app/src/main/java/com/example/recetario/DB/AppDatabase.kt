package com.example.recetario.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recetario.DAO.RecetaDAO
import com.example.recetario.DAO.TIpoComidaDAO
import com.example.recetario.DATA.Receta
import com.example.recetario.DATA.TipoComida

@Database(
    entities = [Receta::class, TipoComida::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun recetas(): RecetaDAO
    abstract fun tiposComida(): TIpoComidaDAO

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE

            if(tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()

                INSTANCE = instance

                return instance
            }
        }
    }
}