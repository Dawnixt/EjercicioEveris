package com.example.weatherapp.Retrofit

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.weatherapp.Entities.Provincia
import com.example.weatherapp.ViewModel.MainViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProvinciaCallback: Callback<ArrayList<Provincia>> {

    val SERVER_URL = "https://www.el-tiempo.net/api/json/"
    var viewModel: MainViewModel = MainViewModel()

    //Mover a ViewModel
    fun start(owner: ViewModelStoreOwner){
        var gson: Gson = GsonBuilder().setLenient().create()
        viewModel = ViewModelProvider(owner).get(MainViewModel::class.java)

        var retrofit: Retrofit = Retrofit.Builder().baseUrl(SERVER_URL).addConverterFactory(GsonConverterFactory.create()).build()
        var provinciaInterface: ProvinciaInterface = retrofit.create(ProvinciaInterface::class.java)

        var call: Call<ArrayList<Provincia>> = provinciaInterface.getProvincias()
        call.enqueue(this)
    }

    override fun onResponse(call: Call<ArrayList<Provincia>>,response: Response<ArrayList<Provincia>>) {
        if (response.isSuccessful){
            var provincia: ArrayList<Provincia> = response.body() ?: ArrayList()
            viewModel.listaProvincia.value = provincia
        }
        else{
            viewModel.setMensaje(response.errorBody().toString())
        }
    }

    override fun onFailure(call: Call<ArrayList<Provincia>>, t: Throwable) {
        viewModel.setMensaje(t.message ?: "")
    }


}