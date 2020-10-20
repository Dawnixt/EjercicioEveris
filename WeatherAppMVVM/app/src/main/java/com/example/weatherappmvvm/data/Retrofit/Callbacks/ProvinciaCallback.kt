package com.example.weatherappmvvm.data.retrofit.callbacks

import androidx.lifecycle.MutableLiveData
import com.example.weatherappmvvm.domain.model.Provincia
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProvinciaCallback: Callback<ArrayList<Provincia>> {

    var mensaje: MutableLiveData<String> = MutableLiveData()
    var listaProvincias: MutableLiveData<ArrayList<Provincia>> = MutableLiveData()

    override fun onFailure(call: Call<ArrayList<Provincia>>, t: Throwable) {
        mensaje.value = t.message
    }

    override fun onResponse(call: Call<ArrayList<Provincia>>,response: Response<ArrayList<Provincia>>) {
        if (response.isSuccessful){
            val provincia: ArrayList<Provincia> = response.body() ?: ArrayList()
            listaProvincias.value = provincia
        }
        else{
            mensaje.value = response.errorBody().toString()
        }
    }
}