package com.example.weatherappmvvm.presentation.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.weatherappmvvm.R
import com.example.weatherappmvvm.domain.Model.MunicipioTiempo
import com.example.weatherappmvvm.presentation.ViewModel.WeatherInfoFactory
import com.example.weatherappmvvm.presentation.ViewModel.WeatherInfoViewModel
import com.example.weatherappmvvm.presentation.Alert.LoadingDialog
import java.util.*

class FragmentMunicipioWeather(var lifeowner: LifecycleOwner, var modelOwner: ViewModelStoreOwner): Fragment() {

    var weatherInfoViewModel: WeatherInfoViewModel = WeatherInfoViewModel(lifeowner,modelOwner)
    var weatherInfoFactory: WeatherInfoFactory = WeatherInfoFactory(lifeowner,modelOwner)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.municipio_weather_fragment,container,false)
        val textViewNombreMunicipio = root.findViewById<TextView>(R.id.TextViewNombreMunicipio)
        val textViewTemperatura = root.findViewById<TextView>(R.id.TextViewTemperatura)
        val textViewHumedad = root.findViewById<TextView>(R.id.TextViewHumedad)
        val textViewTiempo = root.findViewById<TextView>(R.id.TextViewTiempo)
        val textViewTemperaturaManiana = root.findViewById<TextView>(R.id.TextViewTemperaturaManiana)
        val loading: LoadingDialog = LoadingDialog(requireActivity())

        loading.startDialog()
        weatherInfoViewModel = ViewModelProvider(requireActivity(),weatherInfoFactory).get(WeatherInfoViewModel::class.java)

        //Observo para cuando el municipio con el tiempo poner los datos en los TextViews
        weatherInfoViewModel.municipioConTiempo.observe(lifeowner, object: Observer<MunicipioTiempo>{
            override fun onChanged(t: MunicipioTiempo?) {
                if (t != null) {
                    textViewNombreMunicipio.text =  t.municipio.NOMBRE.toUpperCase(Locale.ROOT)
                    textViewTemperatura.text = t.temperatura_actual.plus("º")
                    textViewHumedad.text = t.humedad
                    textViewTiempo.text = t.stateSky.description
                    textViewTemperaturaManiana.text = t.pronostico.manana.temperatura[0].plus("º aprox")
                    loading.dismissDialog()
                }
            }
        })
        return root
    }

}