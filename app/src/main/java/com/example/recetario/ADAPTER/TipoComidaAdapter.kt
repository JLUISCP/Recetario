package com.example.recetario.ADAPTER

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recetario.R
import com.example.recetario.DATA.TipoComida
import com.example.recetario.ListaRecetas
import com.example.recetario.databinding.ItemTipocomidaBinding
import com.squareup.picasso.Picasso

class TipoComidaAdapter(val tiposComida: List<TipoComida>): RecyclerView.Adapter<TipoComidaAdapter.TipoComidaHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipoComidaHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TipoComidaHolder(layoutInflater.inflate(R.layout.item_tipocomida, parent, false))
    }

    override fun onBindViewHolder(holder: TipoComidaHolder, position: Int) {
        holder.render(tiposComida[position])
    }

    override fun getItemCount(): Int = tiposComida.size

    class TipoComidaHolder(val view:View):RecyclerView.ViewHolder(view){

        val binding = ItemTipocomidaBinding.bind(view)

        fun render(tipoComida: TipoComida){
            binding.tvTipoComida.text = tipoComida.nombreTipoComida
            Picasso.get().load(tipoComida.imagen).into(binding.ivTipoComida)
            view.setOnClickListener{
                val intent = Intent(view.context.applicationContext, ListaRecetas::class.java)
                intent.putExtra("idTipoComida", tipoComida.idTipoComida)
                view.context.startActivity(intent)
            }
        }
    }
}