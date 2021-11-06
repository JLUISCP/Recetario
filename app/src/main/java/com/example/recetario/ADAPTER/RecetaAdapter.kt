package com.example.recetario.ADAPTER

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recetario.DATA.Receta
import com.example.recetario.R
import com.example.recetario.VisualizarReceta
import com.example.recetario.databinding.ItemRecetaBinding
import com.squareup.picasso.Picasso

class RecetaAdapter(val recetas: List<Receta>): RecyclerView.Adapter<RecetaAdapter.RecetaHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecetaHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RecetaHolder(layoutInflater.inflate(R.layout.item_receta, parent, false))
    }

    override fun onBindViewHolder(holder: RecetaHolder, position: Int) {
        holder.render(recetas[position])
    }

    override fun getItemCount(): Int = recetas.size

    class RecetaHolder(val view: View):RecyclerView.ViewHolder(view){

        val binding = ItemRecetaBinding.bind(view)

        fun render(receta: Receta){
            binding.tvNombreReceta.text = receta.nombreReceta
            binding.tvCantidadPersonas.text = "Para: ${receta.cantidadPersonas} personas"
            binding.tvTiempoPreparacion.text = "Preparacion: ${receta.tiempoPreparacion} min"
            Picasso.get().load(receta.imagen).into(binding.ivReceta)
            view.setOnClickListener{
                val intent = Intent(view.context.applicationContext, VisualizarReceta::class.java)
                intent.putExtra("idReceta", receta.idReceta)
                view.context.startActivity(intent)
            }
        }
    }
}