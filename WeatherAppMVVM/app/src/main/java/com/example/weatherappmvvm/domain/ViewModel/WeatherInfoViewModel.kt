package com.example.weatherappmvvm.domain.ViewModel

import androidx.lifecycle.*
import com.example.weatherappmvvm.data.Retrofit.Callbacks.MunicipioCallback
import com.example.weatherappmvvm.data.Retrofit.Interfaces.MunicipioInterface
import com.example.weatherappmvvm.data.Retrofit.Callbacks.MunicipioTiempoCallback
import com.example.weatherappmvvm.data.Retrofit.Interfaces.MunicipioTiempoInterface
import com.example.weatherappmvvm.domain.Model.Municipio
import com.example.weatherappmvvm.domain.Model.MunicipioTiempo
import com.example.weatherappmvvm.domain.Model.Municipios
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherInfoViewModel(var lifecycleOwner: LifecycleOwner, var modelOwner: ViewModelStoreOwner): ViewModel() {

    var listaMunicipio:MutableLiveData<ArrayList<Municipio>> = MutableLiveData()
    var idMunicipioSeleccionada: MutableLiveData<String> = MutableLiveData()
    var idProvincia: MutableLiveData<String> = MutableLiveData()
    var municipioConTiempo: MutableLiveData<MunicipioTiempo> = MutableLiveData()
    var municipioTiempoCallback: MunicipioTiempoCallback =
        MunicipioTiempoCallback()
    var municipioCallback: MunicipioCallback =
        MunicipioCallback(
            lifecycleOwner
        )
    var mensaje: MutableLiveData<String> = MutableLiveData()

    val SERVER_URL = "https://www.el-tiempo.net/api/json/"

    /**
     * Esta funcion llamara a la api para cpnseguir la lista de municipios de una provincia y observara para cuando lleguen los datos
     */
    fun getListadoMunicipiosDeProvincia(){
        var gson: Gson = GsonBuilder().setLenient().create()

        val retrofit: Retrofit = Retrofit.Builder().baseUrl(SERVER_URL).addConverterFactory(
            GsonConverterFactory.create()).build()
        val municipioInterface: MunicipioInterface = retrofit.create(
            MunicipioInterface::class.java)

        val call: Call<Municipios> = municipioInterface.getMunicipios(idProvincia.value ?: "")

        call.enqueue(municipioCallback)

        municipioCallback.listaMunicipios.observe(lifecycleOwner,object: Observer<ArrayList<Municipio>>{
            override fun onChanged(t: ArrayList<Municipio>?) {
                listaMunicipio.value = t
            }
        })

        municipioCallback.mensaje.observe(lifecycleOwner, object: Observer<String>{
            override fun onChanged(t: String?) {
                mensaje.value = t
            }
        })
    }

    /**
     * Esta funcion llamara a la api para conseguir el tiempo de un municipio y observara para cuando lleguen los datos
     */
    fun getMunicipioConTiemppo(){
        var gson: Gson = GsonBuilder().setLenient().create()
        val retrofit: Retrofit = Retrofit.Builder().baseUrl(SERVER_URL).addConverterFactory(GsonConverterFactory.create()).build()
        val municipiotiempoInterface: MunicipioTiempoInterface = retrofit.create(
            MunicipioTiempoInterface::class.java)

        val call: Call<MunicipioTiempo> = municipiotiempoInterface.getTiempoMunicipio(idProvincia.value ?: "", idMunicipioSeleccionada.value ?: "")

        call.enqueue(municipioTiempoCallback)

        municipioTiempoCallback.mensaje.observe(lifecycleOwner, object: Observer<String>{
            override fun onChanged(t: String?) {
                mensaje.value = t
            }
        } )

        municipioTiempoCallback.municipioConTiempo.observe(lifecycleOwner, object: Observer<MunicipioTiempo>{
            override fun onChanged(t: MunicipioTiempo?) {
                municipioConTiempo.value = t
            }
        })

    }

}

class WeatherInfoFactory(var lifecycleOwner: LifecycleOwner,var modelOwner: ViewModelStoreOwner): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherInfoViewModel(lifecycleOwner,modelOwner) as T
    }
}