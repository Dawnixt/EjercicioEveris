package com.example.weatherappmvvm.presentation.lista.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherappmvvm.R
import com.example.weatherappmvvm.domain.model.Provincia
import com.example.weatherappmvvm.presentation.lista.viewHolder.ProvinciaViewHolder
import com.example.weatherappmvvm.presentation.viewModel.MyFactory

class ProvinciaRecyclerAdapter(var owner: LifecycleOwner,var provincias: ArrayList<Provincia>, var storeOwner: ViewModelStoreOwner, var viewModelFactory: MyFactory): RecyclerView.Adapter<ProvinciaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvinciaViewHolder {
        return ProvinciaViewHolder(owner,LayoutInflater.from(parent.context).inflate(R.layout.viewholder,parent,false), storeOwner, viewModelFactory)
    }

    override fun onBindViewHolder(holder: ProvinciaViewHolder, position: Int) {
        holder.bind(provincias[position])
    }

    override fun getItemCount(): Int {
        return provincias.size
    }
}


