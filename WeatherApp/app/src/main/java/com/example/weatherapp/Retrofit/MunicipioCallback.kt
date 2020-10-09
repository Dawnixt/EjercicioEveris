package com.example.weatherapp.Retrofit

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
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
    var weatherInfoViewModel: WeatherInfoViewModel? = null

    /**
     * Esta funcion pedira la lista de municipios de una provincia en concreto a la api
     */
    fun start(owner: ViewModelStoreOwner){
        var gson: Gson = GsonBuilder().setLenient().create()
        weatherInfoViewModel = WeatherInfoViewModel(lifeowner,owner)
        weatherInfoViewModel = ViewModelProvider(owner).get(WeatherInfoViewModel::class.java)

        val retrofit: Retrofit = Retrofit.Builder().baseUrl(SERVER_URL).addConverterFactory(GsonConverterFactory.create()).build()
        val municipioInterface: MunicipioInterface = retrofit.create(MunicipioInterface::class.java)

        val call: Call<Municipios> = municipioInterface.getMunicipios(weatherInfoViewModel!!.idProvincia.value ?: "")

        call.enqueue(this)

    }

    override fun onResponse(call: Call<Municipios>, response: Response<Municipios>) {
        if (response.isSuccessful){
            val municipios: Municipios = response.body() ?: Municipios()
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