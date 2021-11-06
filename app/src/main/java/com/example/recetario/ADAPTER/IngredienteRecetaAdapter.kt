package com.example.recetario.ADAPTER

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recetario.R
import com.example.recetario.databinding.ItemIngredientepasoBinding


class IngredienteRecetaAdapter(val ingredienteReceta: List<String>): RecyclerView.Adapter<IngredienteRecetaAdapter.IngredienteRecetaHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredienteRecetaHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return IngredienteRecetaHolder(layoutInflater.inflate(R.layout.item_ingredientepaso, parent, false))
    }

    override fun onBindViewHolder(holder: IngredienteRecetaHolder, position: Int) {
        holder.render(ingredienteReceta[position])
    }

    override fun getItemCount(): Int = ingredienteReceta.size

    class IngredienteRecetaHolder(val view: View):RecyclerView.ViewHolder(view){

        val binding = ItemIngredientepasoBinding.bind(view)

        fun render(ingredienteReceta: String){
            binding.idInformacion.text = ingredienteReceta
        }
    }
}