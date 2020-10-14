package com.example.weatherappmvvm.domain.Lista

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherappmvvm.R
import com.example.weatherappmvvm.domain.Model.Municipio
import com.example.weatherappmvvm.domain.ViewModel.WeatherInfoFactory
import com.example.weatherappmvvm.domain.ViewModel.WeatherInfoViewModel
import kotlinx.android.synthetic.main.viewholder.view.*

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

class MunicipioViewHolder(view: View, storeOwner: ViewModelStoreOwner, weatherInfoFactory: WeatherInfoFactory, lifecycleOwner: LifecycleOwner): RecyclerView.ViewHolder(view), View.OnClickListener{

    var textViewNombreMunicipio = view.TextViewNombre
    var weatherInfoViewModel: WeatherInfoViewModel = WeatherInfoViewModel(lifecycleOwner,storeOwner)

    init {
        view.setOnClickListener(this)
        weatherInfoViewModel = ViewModelProvider(storeOwner,weatherInfoFactory).get(WeatherInfoViewModel::class.java)
    }

    fun bind(row: Municipio){
        textViewNombreMunicipio.text = row.NOMBRE
    }

    override fun onClick(v: View?) {

        val arrayCodigoMunicipio = weatherInfoViewModel.listaMunicipio.value?.get(adapterPosition)?.CODIGOINE?.toCharArray()
        val idMunicipio = "${arrayCodigoMunicipio?.get(0)}${arrayCodigoMunicipio?.get(1)}${arrayCodigoMunicipio?.get(2)}${arrayCodigoMunicipio?.get(3)}${arrayCodigoMunicipio?.get(4)}"

        weatherInfoViewModel.idMunicipioSeleccionada.value = idMunicipio
    }

}