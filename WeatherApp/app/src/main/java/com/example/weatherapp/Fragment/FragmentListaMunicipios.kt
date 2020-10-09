package com.example.weatherapp.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.Lista.MunicipioRecyclerAdapter
import com.example.weatherapp.R
import com.example.weatherapp.ViewModel.WeatherInfoFactory
import com.example.weatherapp.ViewModel.WeatherInfoViewModel

class FragmentListaMunicipios(var lifeowner: LifecycleOwner,var modelOwner: ViewModelStoreOwner): Fragment() {

    var weatherInfoViewModel: WeatherInfoViewModel = WeatherInfoViewModel(lifeowner,modelOwner)
    var weatherInfoFactory: WeatherInfoFactory = WeatherInfoFactory(lifeowner,modelOwner)
    var listaMunicipioRecyclerAdapter: MunicipioRecyclerAdapter? = null
    var listaMunicipiosPorProvincia: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.lista_municipios_fragment,container,false)
        weatherInfoViewModel = ViewModelProvider(requireActivity(),weatherInfoFactory).get(WeatherInfoViewModel::class.java)

        listaMunicipiosPorProvincia = root.findViewById(R.id.ListaMunicipiosdeProvincia)

        //Las cosas clicables 48 dp de diametro

        //Observo la lista de municipios para que cuando lleguen los datos se construya el adaptador del recyclerView
        weatherInfoViewModel.listaMunicipio.observe(requireActivity()){
            listaMunicipioRecyclerAdapter = MunicipioRecyclerAdapter(it,modelOwner,weatherInfoFactory,lifeowner)
            listaMunicipiosPorProvincia?.apply {
                layoutManager = LinearLayoutManager(root.context)
                adapter = listaMunicipioRecyclerAdapter
            }
        }

        return root

    }

}