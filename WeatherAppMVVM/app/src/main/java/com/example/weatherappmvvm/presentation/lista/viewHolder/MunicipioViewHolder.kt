package com.example.weatherappmvvm.presentation.lista.viewHolder

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherappmvvm.domain.model.Municipio
import com.example.weatherappmvvm.presentation.viewModel.WeatherInfoFactory
import com.example.weatherappmvvm.presentation.viewModel.WeatherInfoViewModel
import kotlinx.android.synthetic.main.viewholder.view.*

class MunicipioViewHolder(view: View, storeOwner: ViewModelStoreOwner, weatherInfoFactory: WeatherInfoFactory, lifecycleOwner: LifecycleOwner): RecyclerView.ViewHolder(view), View.OnClickListener{

    var textViewNombreMunicipio = view.TextViewNombre
    var weatherInfoViewModel: WeatherInfoViewModel = WeatherInfoViewModel(lifecycleOwner)

    init {
        view.setOnClickListener(this)
        weatherInfoViewModel = ViewModelProvider(storeOwner,weatherInfoFactory).get(
            WeatherInfoViewModel::class.java)
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