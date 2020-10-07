package com.example.weatherapp.Retrofit

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.weatherapp.Entities.MunicipioTiempo
import com.example.weatherapp.Entities.Municipios
import com.example.weatherapp.ViewModel.WeatherInfoViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MunicipioCallback(var lifeowner: LifecycleOwner): Callback<Municipios> {

    val SERVER_URL = "https://www.el-tiempo.net/api/json/"
    //var viewModel: MainViewModel = MainViewModel()
    var weatherInfoViewModel: WeatherInfoViewModel? = null
    var idProvincia: String = ""

    fun start(owner: ViewModelStoreOwner){
        var gson: Gson = GsonBuilder().setLenient().create()
        weatherInfoViewModel = WeatherInfoViewModel(lifeowner,owner)
        //viewModel = ViewModelProvider(owner).get(MainViewModel::class.java)
        weatherInfoViewModel = ViewModelProvider(owner).get(WeatherInfoViewModel::class.java)

        var retrofit: Retrofit = Retrofit.Builder().baseUrl(SERVER_URL).addConverterFactory(GsonConverterFactory.create()).build()
        var municipioInterface: MunicipioInterface = retrofit.create(MunicipioInterface::class.java)

        /*viewModel.idProvinciaSeleccionada.observe(lifeowner){
            idProvincia = it
        }*/

        var call: Call<Municipios> = municipioInterface.getMunicipios(weatherInfoViewModel!!.idProvincia.value ?: "")
        //var call: Call<Municipios> = municipioInterface.getMunicipios("02")

        call.enqueue(this)

    }

    //TODO Mover todo esto a otra clase
    fun conseguirMunicipioConTiempo(owner: ViewModelStoreOwner){
        var gson: Gson = GsonBuilder().setLenient().create()
        weatherInfoViewModel = WeatherInfoViewModel(lifeowner,owner)
        weatherInfoViewModel = ViewModelProvider(owner).get(WeatherInfoViewModel::class.java)

        var retrofit: Retrofit = Retrofit.Builder().baseUrl(SERVER_URL).addConverterFactory(GsonConverterFactory.create()).build()
        var municipioInterface: MunicipioInterface = retrofit.create(MunicipioInterface::class.java)

        var call: Call<MunicipioTiempo> = municipioInterface.getTiempoMunicipio(weatherInfoViewModel!!.idProvincia.value ?: "",
            weatherInfoViewModel!!.idMunicipioSeleccionada.value ?: "")

        //call.enqueue(this)
    }

    override fun onResponse(call: Call<Municipios>, response: Response<Municipios>) {
        if (response.isSuccessful){
            var municipios: Municipios = response.body() ?: Municipios()
            weatherInfoViewModel?.listaMunicipio?.value = municipios.municipios
        }
        else{
            weatherInfoViewModel?.setMensaje(response.errorBody().toString())
        }
    }

    override fun onFailure(call: Call<Municipios>, t: Throwable) {
        weatherInfoViewModel?.setMensaje(t.message ?: "")
    }


}