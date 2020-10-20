package com.example.weatherappmvvm.presentation.fragment

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
import com.example.weatherappmvvm.R
import com.example.weatherappmvvm.domain.model.MunicipioTiempo
import com.example.weatherappmvvm.presentation.viewModel.WeatherInfoFactory
import com.example.weatherappmvvm.presentation.viewModel.WeatherInfoViewModel
import com.example.weatherappmvvm.presentation.alert.LoadingDialog
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

class FragmentMunicipioWeather(var lifeowner: LifecycleOwner): Fragment(),DialogInterface.OnShowListener {

    var weatherInfoViewModel: WeatherInfoViewModel = WeatherInfoViewModel(lifeowner)
    var weatherInfoFactory: WeatherInfoFactory = WeatherInfoFactory(lifeowner)
    lateinit var textViewNombreMunicipio: TextView
    lateinit var textViewTemperatura: TextView
    lateinit var textViewHumedad: TextView
    lateinit var textViewTiempo: TextView
    lateinit var textViewTemperaturaManiana: TextView
    var dataloaded = false
    var aux: MunicipioTiempo = MunicipioTiempo()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.municipio_weather_fragment,container,false)
        textViewNombreMunicipio = root.findViewById<TextView>(R.id.TextViewNombreMunicipio)
        textViewTemperatura = root.findViewById<TextView>(R.id.TextViewTemperatura)
        textViewHumedad = root.findViewById<TextView>(R.id.TextViewHumedad)
        textViewTiempo = root.findViewById<TextView>(R.id.TextViewTiempo)
        textViewTemperaturaManiana = root.findViewById<TextView>(R.id.TextViewTemperaturaManiana)
        val loadingDialog: LoadingDialog = LoadingDialog(requireActivity())
        loadingDialog.startDialog()
        loadingDialog.dialog.setOnShowListener(this)
        weatherInfoViewModel = ViewModelProvider(requireActivity(),weatherInfoFactory).get(WeatherInfoViewModel::class.java)
        loadingDialog.show()

        //Observo para cuando el municipio con el tiempo poner los datos en los TextViews
        weatherInfoViewModel.municipioConTiempo.observe(lifeowner, object: Observer<MunicipioTiempo>{
            override fun onChanged(t: MunicipioTiempo?) {
                if (t != null) {
                    if (aux != t){
                        textViewNombreMunicipio.text = t.municipio.NOMBRE.toUpperCase(Locale.ROOT)
                        textViewTemperatura.text = t.temperatura_actual.plus("º")
                        textViewHumedad.text = t.humedad
                        textViewTiempo.text = t.stateSky.description
                        textViewTemperaturaManiana.text = t.pronostico.manana.temperatura.get(0).plus("º aprox")
                        dataloaded = true
                        aux = t
                    }
                }
            }
        })
        return root
    }

    override fun onShow(dialog: DialogInterface?) {
        GlobalScope.launch {
            while (!dataloaded){
                delay(1)
            }
            dialog?.dismiss()
            dataloaded = false
        }
    }

}