package com.example.weatherapp.Retrofit

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.Entities.Municipio
import com.example.weatherapp.ViewModel.MainViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MunicipioCallback(var lifeowner: LifecycleOwner): Callback<ArrayList<Municipio>> {

    val SERVER_URL = "https://www.el-tiempo.net/api/json/"
    var viewModel: MainViewModel = MainViewModel()
    var idProvincia: String = ""

    fun Start(owner: ViewModelStoreOwner){
        var gson: Gson = GsonBuilder().setLenient().create()
        viewModel = ViewModelProvider(owner).get(MainViewModel::class.java)

        var retrofit: Retrofit = Retrofit.Builder().baseUrl(SERVER_URL).addConverterFactory(GsonConverterFactory.create()).build()
        var municipioInterface: MunicipioInterface = retrofit.create(MunicipioInterface::class.java)

        viewModel.idProvinciaSeleccionada.observe(lifeowner){
            idProvincia = it
        }

        var call: Call<ArrayList<Municipio>> = municipioInterface.getMunicipios(idProvincia)

        call.enqueue(this)

    }

    override fun onResponse(call: Call<ArrayList<Municipio>>, response: Response<ArrayList<Municipio>>) {
        if (response.isSuccessful){
            var municipios: ArrayList<Municipio> = response.body() ?: ArrayList()
            viewModel.listaMunicipio.value = municipios
        }
        else{
            viewModel.setMensaje(response.errorBody().toString())
        }
    }

    override fun onFailure(call: Call<ArrayList<Municipio>>, t: Throwable) {
        viewModel.setMensaje(t.message ?: "")
    }


}