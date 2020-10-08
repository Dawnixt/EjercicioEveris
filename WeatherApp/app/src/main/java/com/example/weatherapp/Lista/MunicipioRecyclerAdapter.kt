package com.example.weatherapp.Lista

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.Entities.Municipio
import com.example.weatherapp.R
import com.example.weatherapp.ViewModel.WeatherInfoFactory
import com.example.weatherapp.ViewModel.WeatherInfoViewModel
import kotlinx.android.synthetic.main.viewholder.view.*

class MunicipioRecyclerAdapter(var municipios: ArrayList<Municipio>, var storeOwner: ViewModelStoreOwner, var weatherInfoFactory: WeatherInfoFactory, var lifecycleOwner: LifecycleOwner): RecyclerView.Adapter<MunicipioViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MunicipioViewHolder {
        return MunicipioViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.viewholder,parent,false),storeOwner,weatherInfoFactory,lifecycleOwner)
    }

    override fun onBindViewHolder(holder: MunicipioViewHolder, position: Int) {
        holder.bind(municipios[position])
    }

    override fun getItemCount(): Int {
        return municipios.size
    }
}

class MunicipioViewHolder(view: View, storeOwner: ViewModelStoreOwner, weatherInfoFactory: WeatherInfoFactory,lifecycleOwner: LifecycleOwner): RecyclerView.ViewHolder(view), View.OnClickListener{

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

        var CODarray = weatherInfoViewModel.listaMunicipio.value?.get(adapterPosition)?.CODIGOINE?.toCharArray()
        var idMunicipio = "${CODarray?.get(0)}${CODarray?.get(1)}${CODarray?.get(2)}${CODarray?.get(3)}${CODarray?.get(4)}"

        weatherInfoViewModel.idMunicipioSeleccionada.value = idMunicipio
    }

}