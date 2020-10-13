package com.example.weatherapp.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import com.example.weatherapp.Alert.LoadingDialog
import com.example.weatherapp.Entities.MunicipioTiempo
import com.example.weatherapp.R
import com.example.weatherapp.ViewModel.WeatherInfoFactory
import com.example.weatherapp.ViewModel.WeatherInfoViewModel
import kotlinx.android.synthetic.main.municipio_weather_fragment.*

class FragmentMunicipioWeather(var lifeowner: LifecycleOwner, var modelOwner: ViewModelStoreOwner): Fragment() {

    var weatherInfoViewModel: WeatherInfoViewModel = WeatherInfoViewModel(lifeowner,modelOwner)
    var weatherInfoFactory: WeatherInfoFactory = WeatherInfoFactory(lifeowner,modelOwner)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val loading: LoadingDialog = LoadingDialog(requireActivity())
        val root = inflater.inflate(R.layout.municipio_weather_fragment,container,false)
        weatherInfoViewModel = ViewModelProvider(requireActivity(),weatherInfoFactory).get(WeatherInfoViewModel::class.java)

        loading.startDialog()
        //Observo el municipio con el tiempo para que cuando llegue la informacion ponerla en pantalla
        weatherInfoViewModel.municipioTiempo.observe(lifeowner){ it ->
            TextViewNombreMunicipio.text = it.municipio.NOMBRE
            TextViewTemperatura.text = it.temperatura_actual.plus("º")
            TextViewHumedad.text = it.humedad
            TextViewTiempo.text = it.stateSky.description
            TextViewTemperaturaManiana.text = it.pronostico.manana.temperatura[0].plus("º aprox")
            loading.dismissDialog()
        }

        return root
    }

}