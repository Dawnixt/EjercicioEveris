package com.example.weatherapp.Retrofit

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.weatherapp.Entities.MunicipioTiempo
import com.example.weatherapp.ViewModel.WeatherInfoViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MunicipioTiempoCallback(var lifeowner: LifecycleOwner): Callback<MunicipioTiempo> {

    val SERVER_URL = "https://www.el-tiempo.net/api/json/"
    var weatherInfoViewModel: WeatherInfoViewModel? = null

    /**
     * Esta funcion pedira la informacion del tiempo de un municipio
     */
    fun start(owner: ViewModelStoreOwner){

        var gson: Gson = GsonBuilder().setLenient().create()
        weatherInfoViewModel = WeatherInfoViewModel(lifeowner,owner)
        weatherInfoViewModel = ViewModelProvider(owner).get(WeatherInfoViewModel::class.java)

        var retrofit: Retrofit = Retrofit.Builder().baseUrl(SERVER_URL).addConverterFactory(GsonConverterFactory.create()).build()
        var municipiotiempoInterface: MunicipioTiempoInterface = retrofit.create(MunicipioTiempoInterface::class.java)

        var call: Call<MunicipioTiempo> = municipiotiempoInterface.getTiempoMunicipio(weatherInfoViewModel!!.idProvincia.value ?: "",
            weatherInfoViewModel!!.idMunicipioSeleccionada.value ?: "")

        call.enqueue(this)

    }

    override fun onResponse(call: Call<MunicipioTiempo>, response: Response<MunicipioTiempo>) {
        if (response.isSuccessful){
            var municipioTiempo: MunicipioTiempo = response.body() ?: MunicipioTiempo()
            weatherInfoViewModel?.municipioTiempo?.value = municipioTiempo
        }
        else{
            weatherInfoViewModel?.setMensaje(response.errorBody().toString())
        }
    }

    override fun onFailure(call: Call<MunicipioTiempo>, t: Throwable) {
        weatherInfoViewModel?.setMensaje(t.message ?: "")
    }
}