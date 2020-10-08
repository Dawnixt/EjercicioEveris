package com.example.weatherapp.Lista

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.Entities.Provincia
import com.example.weatherapp.R
import com.example.weatherapp.ViewModel.MainViewModel
import com.example.weatherapp.ViewModel.MyFactory
import kotlinx.android.synthetic.main.viewholder.view.*


class ProvinciaRecyclerAdapter(var provincias: ArrayList<Provincia>,var storeOwner: ViewModelStoreOwner, var viewModelFactory: MyFactory): RecyclerView.Adapter<ProvinciaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvinciaViewHolder {
        return ProvinciaViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.viewholder,parent,false), storeOwner, viewModelFactory)
    }

    override fun onBindViewHolder(holder: ProvinciaViewHolder, position: Int) {
        holder.bind(provincias[position])
    }

    override fun getItemCount(): Int {
        return provincias.size
    }
}


class ProvinciaViewHolder(view: View, storeOwner: ViewModelStoreOwner, viewModelFactory: MyFactory): RecyclerView.ViewHolder(view), View.OnClickListener{

    var mainViewModel: MainViewModel = MainViewModel()

    init {
        view.setOnClickListener(this)
        mainViewModel = ViewModelProvider(storeOwner,viewModelFactory).get(MainViewModel::class.java)

    }

    var textViewNombreProvincia = view.TextViewNombre

    fun bind(row: Provincia){
        textViewNombreProvincia.text = row.NOMBRE_PROVINCIA
    }

    override fun onClick(v: View?) {

       mainViewModel.idProvinciaSeleccionada.value = mainViewModel.listaProvincia.value?.get(adapterPosition)?.CODPROV

    }

}