package com.example.weatherappmvvm.data.Retrofit.Callbacks

import androidx.lifecycle.MutableLiveData
import com.example.weatherappmvvm.domain.Model.MunicipioTiempo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MunicipioTiempoCallback: Callback<MunicipioTiempo> {

    var mensaje: MutableLiveData<String> = MutableLiveData()
    var municipioConTiempo: MutableLiveData<MunicipioTiempo> = MutableLiveData()

    override fun onFailure(call: Call<MunicipioTiempo>, t: Throwable) {
        mensaje.value = t.message
    }

    override fun onResponse(call: Call<MunicipioTiempo>, response: Response<MunicipioTiempo>) {
        if (response.isSuccessful){
            val municipioTiempo: MunicipioTiempo = response.body() ?: MunicipioTiempo()
            municipioConTiempo.value = municipioTiempo
        }
        else{
            mensaje.value = response.errorBody().toString()
        }
    }
}