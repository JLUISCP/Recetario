package com.example.recetario

import android.database.sqlite.SQLiteException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recetario.ADAPTER.RecetaAdapter
import com.example.recetario.DATA.Receta
import com.example.recetario.DB.AppDatabase
import com.example.recetario.databinding.ActivityListaRecetasBinding
import com.example.recetario.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class ListaRecetas() : AppCompatActivity() {

    private lateinit var binding: ActivityListaRecetasBinding

    var recetas = emptyList<Receta>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaRecetasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idTipoComida = intent.getIntExtra("idTipoComida", 0)

        val database = AppDatabase.getDatabase(this)
        lifecycleScope.launch{
            try {
                recetas = database.recetas().getRecetas(idTipoComida)
            }catch (sqliteException: SQLiteException){
                Toast.makeText(binding.root.context.applicationContext, "Fallo de conexion a la base de datos", Toast.LENGTH_SHORT).show()
            }
            initRecycler()
        }
    }

    private fun initRecycler() {
        binding.rvRecetas.layoutManager = LinearLayoutManager(this)
        val adapter = RecetaAdapter(recetas)
        binding.rvRecetas.adapter = adapter
    }
}