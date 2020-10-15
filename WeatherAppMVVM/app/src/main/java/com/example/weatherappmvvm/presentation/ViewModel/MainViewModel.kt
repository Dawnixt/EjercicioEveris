package com.example.weatherappmvvm.presentation.ViewModel

import androidx.lifecycle.*
import com.example.weatherappmvvm.data.Retrofit.Callbacks.ProvinciaCallback
import com.example.weatherappmvvm.data.Retrofit.Interfaces.ProvinciaInterface
import com.example.weatherappmvvm.domain.Model.Provincia
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel(var owner: LifecycleOwner): ViewModel() {

    val provinciaCallback: ProvinciaCallback =
        ProvinciaCallback()
    val SERVER_URL = "https://www.el-tiempo.net/api/json/"
    var mensaje: MutableLiveData<String> = MutableLiveData()
    val listadoProvincias: MutableLiveData<ArrayList<Provincia>> = MutableLiveData()
    var idProvinciaSeleccionada: MutableLiveData<String> = MutableLiveData()


    /**
     * Esta funcion llamara a la api para que nos de la lista de provincias y observara los datos para cuando lleguen
     */
    fun getListadoProvincias(){

        var gson: Gson = GsonBuilder().setLenient().create()

        val retrofit: Retrofit = Retrofit.Builder().baseUrl(SERVER_URL).addConverterFactory(
            GsonConverterFactory.create()).build()
        val provinciaInterface: ProvinciaInterface = retrofit.create(
            ProvinciaInterface::class.java)

        val call: Call<ArrayList<Provincia>> = provinciaInterface.getProvincias()
        call.enqueue(provinciaCallback)

        provinciaCallback.listaProvincias.observe(owner, object: Observer<ArrayList<Provincia>>{
            override fun onChanged(t: ArrayList<Provincia>?) {
                listadoProvincias.value = t
            }
        })

        provinciaCallback.mensaje.observe(owner, object: Observer<String>{
            override fun onChanged(t: String?) {
                mensaje.value = t
            }
        })
    }

}

class MyFactory(var owner: LifecycleOwner): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(owner) as T
    }
}

