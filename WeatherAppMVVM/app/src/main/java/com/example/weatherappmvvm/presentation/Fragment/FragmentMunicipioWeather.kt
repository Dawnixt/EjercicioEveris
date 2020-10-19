package com.example.weatherappmvvm.presentation.Fragment

import android.app.AlertDialog
import android.content.DialogInterface
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
import java.util.concurrent.TimeUnit

class FragmentMunicipioWeather(var lifeowner: LifecycleOwner): Fragment(),DialogInterface.OnShowListener {

    var weatherInfoViewModel: WeatherInfoViewModel = WeatherInfoViewModel(lifeowner)
    var weatherInfoFactory: WeatherInfoFactory = WeatherInfoFactory(lifeowner)
    lateinit var textViewNombreMunicipio: TextView
    lateinit var textViewTemperatura: TextView
    lateinit var textViewHumedad: TextView
    lateinit var textViewTiempo: TextView
    var repetition = true
    lateinit var textViewTemperaturaManiana: TextView
    var aux: MunicipioTiempo = MunicipioTiempo()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.municipio_weather_fragment,container,false)
        textViewNombreMunicipio = root.findViewById<TextView>(R.id.TextViewNombreMunicipio)
        textViewTemperatura = root.findViewById<TextView>(R.id.TextViewTemperatura)
        textViewHumedad = root.findViewById<TextView>(R.id.TextViewHumedad)
        textViewTiempo = root.findViewById<TextView>(R.id.TextViewTiempo)
        textViewTemperaturaManiana = root.findViewById<TextView>(R.id.TextViewTemperaturaManiana)
        var loadingDialog: LoadingDialog = LoadingDialog(requireActivity())
        loadingDialog.startDialog()
        loadingDialog.dialog.setOnShowListener(this)
        weatherInfoViewModel = ViewModelProvider(requireActivity(),weatherInfoFactory).get(WeatherInfoViewModel::class.java)

        //Observo para cuando el municipio con el tiempo poner los datos en los TextViews
        weatherInfoViewModel.municipioConTiempo.observe(lifeowner, object: Observer<MunicipioTiempo>{
            override fun onChanged(t: MunicipioTiempo?) {
                if (t != null) {
                    loadingDialog.show()
                    if ( aux != t){
                        repetition = false
                        aux = t
                    }
                }
            }
        })
        return root
    }

    override fun onShow(dialog: DialogInterface?) {
        //Poner la interface en una variable y usarla
        if (!repetition){
            textViewNombreMunicipio.text =  aux.municipio.NOMBRE.toUpperCase(Locale.ROOT)
            textViewTemperatura.text = aux.temperatura_actual.plus("º")
            textViewHumedad.text = aux.humedad
            textViewTiempo.text = aux.stateSky.description
            textViewTemperaturaManiana.text = aux.pronostico.manana.temperatura[0].plus("º aprox")
        }
        //TimeUnit.MILLISECONDS.sleep(3000)
        dialog?.dismiss()
        repetition = true
    }

}