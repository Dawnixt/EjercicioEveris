package com.example.weatherapp.Lista

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.Entities.Municipio
import com.example.weatherapp.R
import kotlinx.android.synthetic.main.viewholder.view.*

class MunicipioRecyclerAdapter(var municipios: ArrayList<Municipio>): RecyclerView.Adapter<MunicipioViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MunicipioViewHolder {
        return MunicipioViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.viewholder,parent,false))
    }

    override fun onBindViewHolder(holder: MunicipioViewHolder, position: Int) {
        holder.bind(municipios[position])
    }

    override fun getItemCount(): Int {
        return municipios.size
    }
}

class MunicipioViewHolder(view: View): RecyclerView.ViewHolder(view){

    var textViewNombreMunicipio = view.TVNombre

    fun bind(row: Municipio){
        textViewNombreMunicipio.text = row.NOMBRE
    }

}