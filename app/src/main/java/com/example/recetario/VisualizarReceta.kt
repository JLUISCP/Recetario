package com.example.recetario

import android.content.Intent
import android.database.sqlite.SQLiteException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recetario.ADAPTER.IngredienteRecetaAdapter
import com.example.recetario.DATA.Receta
import com.example.recetario.DB.AppDatabase
import com.example.recetario.databinding.ActivityVisualizarRecetaBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import java.lang.NumberFormatException

class VisualizarReceta : AppCompatActivity() {

    private lateinit var binding: ActivityVisualizarRecetaBinding
    lateinit var receta: Receta

    var preparacion = emptyList<String>()
    var ingredientes = emptyList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVisualizarRecetaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idReceta = intent.getIntExtra("idReceta", 0)

        val database = AppDatabase.getDatabase(this)
        lifecycleScope.launch{
            try {
                receta = database.recetas().getReceta(idReceta)
            }catch (sqliteException: SQLiteException){
                Toast.makeText(binding.root.context.applicationContext, "Fallo de conexion a la base de datos", Toast.LENGTH_SHORT).show()
            }
            initInterface()
        }
        binding.btnEliminar.setOnClickListener{
            lifecycleScope.launch{
                try {
                    database.recetas().deleteReceta(receta)
                }catch (sqliteException: SQLiteException){
                    Toast.makeText(binding.root.context.applicationContext, "Fallo de conexion a la Base de datos", Toast.LENGTH_SHORT).show()
                }
                val intent = Intent(binding.root.context.applicationContext, MainActivity::class.java)
                Toast.makeText(binding.root.context.applicationContext, "Se ha eliminado la receta: ${receta.nombreReceta}", Toast.LENGTH_SHORT).show()
                binding.root.context.startActivity(intent)
            }
        }
        binding.btnCalcular.setOnClickListener{
            calcularIngredientes()
        }
    }

    private fun calcularIngredientes() {
        val calculoIngredientes: MutableList<String> = mutableListOf()
        try {
            for(item in ingredientes){
                val ingredientesSeparado = item.split("-")
                var cantidadPersonasEsperada = (binding.etCantidadPersonas.text.toString()).toInt()
                var multiplicador: Float = (cantidadPersonasEsperada.toFloat()/receta.cantidadPersonas.toFloat())
                var cantidad: Float = (ingredientesSeparado[0].toFloat() * multiplicador)
                calculoIngredientes.add("${cantidad}-${ingredientesSeparado[1]}")
            }
        }catch (formatException: NumberFormatException){
            Toast.makeText(binding.root.context.applicationContext, "No se ingreso un numero, favor de verificar", Toast.LENGTH_SHORT).show()
        }
        initIngredientes(calculoIngredientes)
    }

    private fun initInterface() {
        binding.tvNombreReceta.text = receta.nombreReceta
        Picasso.get().load(receta.imagen).into(binding.ivImagenReceta)
        binding.tvCantidadPersonas.text = "Para: ${receta.cantidadPersonas} personas"
        binding.tvTiempoPreparacion.text = "Preparacion: ${receta.tiempoPreparacion} min"
        separarCadenas()
    }

    private fun separarCadenas() {
        ingredientes = receta.ingredientes.split("|")
        initIngredientes(ingredientes)

        preparacion = receta.preparacion.split("|")
        initPreparacion(preparacion)
    }

    private fun initPreparacion(preparacion: List<String>) {
        binding.rvPreparacion.layoutManager = LinearLayoutManager(this)
        val adapterPreparacion = IngredienteRecetaAdapter(preparacion)
        binding.rvPreparacion.adapter = adapterPreparacion
    }

    private fun initIngredientes(ingredientes: List<String>) {
        binding.rvIngredientes.layoutManager = LinearLayoutManager(this)
        val adapterIngrediente = IngredienteRecetaAdapter(ingredientes)
        binding.rvIngredientes.adapter = adapterIngrediente
    }
}