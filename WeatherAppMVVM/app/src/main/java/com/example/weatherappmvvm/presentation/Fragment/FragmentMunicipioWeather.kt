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
import com.example.weatherappmvvm.R
import com.example.weatherappmvvm.domain.Model.MunicipioTiempo
import com.example.weatherappmvvm.domain.ViewModel.WeatherInfoFactory
import com.example.weatherappmvvm.domain.ViewModel.WeatherInfoViewModel
import com.example.weatherappmvvm.presentation.Alert.LoadingDialog
import kotlinx.android.synthetic.main.municipio_weather_fragment.*

class FragmentMunicipioWeather(var lifeowner: LifecycleOwner, var modelOwner: ViewModelStoreOwner): Fragment() {

    var weatherInfoViewModel: WeatherInfoViewModel = WeatherInfoViewModel(lifeowner,modelOwner)
    var weatherInfoFactory: WeatherInfoFactory = WeatherInfoFactory(lifeowner,modelOwner)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val loading: LoadingDialog = LoadingDialog(requireActivity())
        val root = inflater.inflate(R.layout.municipio_weather_fragment,container,false)

        weatherInfoViewModel = ViewModelProvider(requireActivity(),weatherInfoFactory).get(WeatherInfoViewModel::class.java)
        loading.startDialog()
        weatherInfoViewModel.getMunicipioConTiemppo()

        //Observo para cuando el municipio con el tiempo poner los datos en los TextViews
        weatherInfoViewModel.municipioConTiempo.observe(lifeowner, object: Observer<MunicipioTiempo>{
            override fun onChanged(t: MunicipioTiempo?) {
                if (t != null) {
                    TextViewNombreMunicipio.text = t.municipio.NOMBRE
                    TextViewTemperatura.text = t.temperatura_actual.plus("º")
                    TextViewHumedad.text = t.humedad
                    TextViewTiempo.text = t.stateSky.description
                    TextViewTemperaturaManiana.text = t.pronostico.manana.temperatura[0].plus("º aprox")
                    loading.dismissDialog()
                }
            }
        })

        return root

    }

}