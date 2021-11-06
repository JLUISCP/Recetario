package com.example.recetario.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.recetario.DATA.TipoComida

@Dao
interface TIpoComidaDAO {
    @Query("SELECT * FROM TipoComida")
    suspend fun getTipoComida(): List<TipoComida>

    @Insert
    suspend fun insertTiposComida(tiposComida: List<TipoComida>)
}