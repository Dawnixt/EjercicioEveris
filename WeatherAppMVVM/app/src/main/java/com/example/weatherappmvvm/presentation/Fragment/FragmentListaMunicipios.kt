package com.example.weatherappmvvm.presentation.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherappmvvm.R
import com.example.weatherappmvvm.presentation.Lista.MunicipioRecyclerAdapter
import com.example.weatherappmvvm.domain.Model.Municipio
import com.example.weatherappmvvm.presentation.ViewModel.WeatherInfoFactory
import com.example.weatherappmvvm.presentation.ViewModel.WeatherInfoViewModel

class FragmentListaMunicipios(var lifeowner: LifecycleOwner, var modelOwner: ViewModelStoreOwner): Fragment() {

    var weatherInfoViewModel: WeatherInfoViewModel = WeatherInfoViewModel(lifeowner)
    var weatherInfoFactory: WeatherInfoFactory = WeatherInfoFactory(lifeowner)
    var listaMunicipioRecyclerAdapter: MunicipioRecyclerAdapter? = null
    var listaMunicipiosPorProvincia: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.lista_municipios_fragment,container,false)
        weatherInfoViewModel = ViewModelProvider(requireActivity(),weatherInfoFactory).get(WeatherInfoViewModel::class.java)

        listaMunicipiosPorProvincia = root.findViewById(R.id.ListaMunicipiosdeProvincia)

        weatherInfoViewModel.getListadoMunicipiosDeProvincia()

        //Observo para cuando llegue la lista crear el adaptor y ponerselo al recycler
        weatherInfoViewModel.listaMunicipio.observe(lifeowner, object: Observer<ArrayList<Municipio>>{
            override fun onChanged(t: ArrayList<Municipio>?) {
                listaMunicipioRecyclerAdapter = MunicipioRecyclerAdapter(t ?: ArrayList(),modelOwner,weatherInfoFactory,lifeowner)
                listaMunicipiosPorProvincia?.apply {
                    layoutManager = LinearLayoutManager(root.context)
                    adapter = listaMunicipioRecyclerAdapter
                }
            }
        })

        return root

    }


}