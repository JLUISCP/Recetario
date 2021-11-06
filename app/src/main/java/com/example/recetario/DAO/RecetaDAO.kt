package com.example.recetario.DAO

import androidx.room.*
import com.example.recetario.DATA.Receta

@Dao
interface RecetaDAO {

    @Query("SELECT * FROM Receta WHERE idTipoComida = :id")
    suspend fun getRecetas(id: Int): List<Receta>

    @Query("SELECT * FROM Receta WHERE idReceta = :id")
    suspend fun getRecetasPorID(id: Int): List<Receta>

    @Query("SELECT * FROM Receta WHERE idReceta = :id")
    suspend fun getReceta(id: Int): Receta

    @Update
    suspend fun updateReceta(receta: Receta)

    @Insert
    suspend fun insertReceta(recetas: List<Receta>)

    @Delete
    suspend fun deleteReceta(recetas: Receta)

}