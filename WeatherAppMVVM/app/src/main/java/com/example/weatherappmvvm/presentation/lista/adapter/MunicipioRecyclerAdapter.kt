package com.example.weatherappmvvm.presentation.lista.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherappmvvm.R
import com.example.weatherappmvvm.domain.model.Municipio
import com.example.weatherappmvvm.presentation.lista.viewHolder.MunicipioViewHolder
import com.example.weatherappmvvm.presentation.viewModel.WeatherInfoFactory

class MunicipioRecyclerAdapter(var municipios: ArrayList<Municipio>, var storeOwner: ViewModelStoreOwner, var weatherInfoFactory: WeatherInfoFactory, var lifecycleOwner: LifecycleOwner): RecyclerView.Adapter<MunicipioViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MunicipioViewHolder {
        return MunicipioViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.viewholder,parent,false),storeOwner,weatherInfoFactory,lifecycleOwner)
    }

    override fun getItemCount(): Int {
        return municipios.size
    }

    override fun onBindViewHolder(holder: MunicipioViewHolder, position: Int) {
        holder.bind(municipios[position])
    }
}
