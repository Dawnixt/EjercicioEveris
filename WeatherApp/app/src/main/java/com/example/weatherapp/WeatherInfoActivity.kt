package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import com.example.weatherapp.Fragment.FragmentListaMunicipios
import com.example.weatherapp.Fragment.FragmentMunicipioWeather
import com.example.weatherapp.ViewModel.WeatherInfoFactory
import com.example.weatherapp.ViewModel.WeatherInfoViewModel

class WeatherInfoActivity : AppCompatActivity() {

    //https://www.codexpedia.com/android/a-simple-android-loader-example/
    //https://stackoverflow.com/questions/51408098/what-is-the-appropriate-replacement-of-deprecated-getsupportloadermanager
    //https://www.grokkingandroid.com/using-loaders-in-android/
    //https://developer.android.com/reference/kotlin/androidx/loader/content/Loader

    var weatherInfoViewModel: WeatherInfoViewModel = WeatherInfoViewModel(this,this)
    var weatherInfoFactory: WeatherInfoFactory = WeatherInfoFactory(this,this)
    var fragmentListaMunicipios: FragmentListaMunicipios = FragmentListaMunicipios(this,this)
    var fragmentMunicipioWeather: FragmentMunicipioWeather = FragmentMunicipioWeather(this,this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_info)

        weatherInfoViewModel = ViewModelProvider(this,weatherInfoFactory).get(WeatherInfoViewModel::class.java)

        weatherInfoViewModel.idProvincia.value = intent.getStringExtra("idProvincia")

        //LoaderManager.getInstance(this)

        supportFragmentManager.beginTransaction().replace(R.id.fragment,fragmentListaMunicipios).commit()

        //Observo cuando cambia el id de municipio seleccionado para cambiar a otro fragment
        weatherInfoViewModel.idMunicipioSeleccionada.observe(this){
            weatherInfoViewModel.obtenerMunicipioConTiempo()
            supportFragmentManager.beginTransaction().replace(R.id.fragment,fragmentMunicipioWeather).commit()
        }

        //Observo el mensaje por si ocurre algun error mandar un mensaje por pantalla
        weatherInfoViewModel.mensaje.observe(this){
            Toast.makeText(this,it,Toast.LENGTH_LONG).show()
        }

        weatherInfoViewModel.obtenerListaMunicipios()

    }
}