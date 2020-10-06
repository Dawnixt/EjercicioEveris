package com.example.weatherapp.Lista

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.Entities.Provincia
import com.example.weatherapp.R
import kotlinx.android.synthetic.main.viewholder.view.*

//El adapter de la lista de provincias
class ProvinciaRecyclerAdapter(var provincias: ArrayList<Provincia>): RecyclerView.Adapter<ProvinciaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvinciaViewHolder {
        return ProvinciaViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.viewholder,parent,false))
    }

    override fun onBindViewHolder(holder: ProvinciaViewHolder, position: Int) {
        holder.bind(provincias[position])
    }

    override fun getItemCount(): Int {
        return provincias.size
    }
}

//El viewHoldwe de la lista de provincias
class ProvinciaViewHolder(view: View): RecyclerView.ViewHolder(view){

    var textViewNombreProvincia = view.TVNombre

    fun bind(row: Provincia){
        textViewNombreProvincia.text = row.NOMBRE_PROVINCIA
    }

}