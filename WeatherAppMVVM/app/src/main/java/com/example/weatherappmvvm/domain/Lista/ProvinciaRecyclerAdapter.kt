package com.example.weatherappmvvm.domain.Lista

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherappmvvm.R
import com.example.weatherappmvvm.domain.Model.Provincia
import com.example.weatherappmvvm.domain.ViewModel.MainViewModel
import com.example.weatherappmvvm.domain.ViewModel.MyFactory
import kotlinx.android.synthetic.main.viewholder.view.*

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

class ProvinciaViewHolder(owner: LifecycleOwner, view: View, storeOwner: ViewModelStoreOwner, viewModelFactory: MyFactory): RecyclerView.ViewHolder(view), View.OnClickListener{

    var mainViewModel: MainViewModel = MainViewModel(owner)

    init {
        view.setOnClickListener(this)
        mainViewModel = ViewModelProvider(storeOwner,viewModelFactory).get(MainViewModel::class.java)

    }

    var textViewNombreProvincia = view.TextViewNombre

    fun bind(row: Provincia){
        textViewNombreProvincia.text = row.NOMBRE_PROVINCIA
    }

    override fun onClick(v: View?) {

        mainViewModel.idProvinciaSeleccionada.value = mainViewModel.listadoProvincias.value?.get(adapterPosition)?.CODPROV

    }

}
