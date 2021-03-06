package com.example.weatherappmvvm.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherappmvvm.R
import com.example.weatherappmvvm.presentation.viewModel.WeatherInfoFactory
import com.example.weatherappmvvm.presentation.viewModel.WeatherInfoViewModel
import com.example.weatherappmvvm.presentation.fragment.FragmentListaMunicipios
import com.example.weatherappmvvm.presentation.fragment.FragmentMunicipioWeather

class WeatherInfoActivity : AppCompatActivity() {

    var weatherInfoViewModel: WeatherInfoViewModel = WeatherInfoViewModel(this)
    var weatherInfoFactory: WeatherInfoFactory = WeatherInfoFactory(this)
    var fragmentListaMunicipios: FragmentListaMunicipios = FragmentListaMunicipios(this,this)
    var fragmentMunicipioWeather: FragmentMunicipioWeather = FragmentMunicipioWeather(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_info)
        weatherInfoViewModel = ViewModelProvider(this,weatherInfoFactory).get(WeatherInfoViewModel::class.java)

        weatherInfoViewModel.idProvincia.value = intent.getStringExtra("idProvincia")

        supportFragmentManager.beginTransaction().replace(R.id.fragment,fragmentListaMunicipios).commit()

        //Observo para cuando seleccione un municipio poner el otro fragment
        weatherInfoViewModel.idMunicipioSeleccionada.observe(this,object: Observer<String>{
            override fun onChanged(t: String?) {
                weatherInfoViewModel.getMunicipioConTiemppo()
                supportFragmentManager.beginTransaction().replace(R.id.fragment,fragmentMunicipioWeather).addToBackStack(null).commit()
            }
        })
    }
}