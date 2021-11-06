package com.example.recetario.DATA

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TipoComida(
    @PrimaryKey(autoGenerate = true)
    val idTipoComida: Int,
    val nombreTipoComida: String,
    val imagen: String
)