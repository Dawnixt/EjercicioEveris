package com.example.weatherappmvvm.data.retrofit.callbacks

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.example.weatherappmvvm.domain.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MunicipioCallback(var lifeowner: LifecycleOwner): Callback<Municipios> {

    var mensaje: MutableLiveData<String> = MutableLiveData()
    var listaMunicipios: MutableLiveData<ArrayList<Municipio>> = MutableLiveData()

    override fun onFailure(call: Call<Municipios>, t: Throwable) {
        mensaje.value = t.message
    }

    override fun onResponse(call: Call<Municipios>, response: Response<Municipios>) {
        if (response.isSuccessful){
            val municipios: Municipios = response.body() ?: Municipios()
            listaMunicipios.value = municipios.municipios
        }
        else{
            mensaje.value = response.errorBody().toString()
        }
    }
}