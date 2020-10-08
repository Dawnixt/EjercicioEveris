package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.Fragment.FragmentListaMunicipios
import com.example.weatherapp.Fragment.FragmentMunicipioWeather
import com.example.weatherapp.ViewModel.WeatherInfoFactory
import com.example.weatherapp.ViewModel.WeatherInfoViewModel

class WeatherInfoActivity : AppCompatActivity() {

    var weatherInfoViewModel: WeatherInfoViewModel = WeatherInfoViewModel(this,this)
    var weatherInfoFactory: WeatherInfoFactory = WeatherInfoFactory(this,this)
    var fragmentListaMunicipios: FragmentListaMunicipios = FragmentListaMunicipios(this,this)
    var fragmentMunicipioWeather: FragmentMunicipioWeather = FragmentMunicipioWeather(this,this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_info)

        weatherInfoViewModel = ViewModelProvider(this,weatherInfoFactory).get(WeatherInfoViewModel::class.java)

        weatherInfoViewModel.idProvincia.value = intent.getStringExtra("idProvincia")

        supportFragmentManager.beginTransaction().replace(R.id.fragment,fragmentListaMunicipios).commit()

        weatherInfoViewModel.idMunicipioSeleccionada.observe(this){
            supportFragmentManager.beginTransaction().replace(R.id.fragment,fragmentMunicipioWeather).commit()
        }

        weatherInfoViewModel.obtenerListaMunicipios()

    }
}