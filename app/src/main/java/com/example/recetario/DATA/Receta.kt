package com.example.recetario.DATA

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Receta(
    @PrimaryKey(autoGenerate = true)
    val idReceta: Int,
    val nombreReceta: String,
    val cantidadPersonas: Int,
    val tiempoPreparacion: Int,
    val ingredientes: String,
    val preparacion: String,
    val imagen: String,
    val idTipoComida: Int
)