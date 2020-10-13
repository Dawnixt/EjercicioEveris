package com.example.weatherappmvvm.data.Retrofit

import com.example.weatherappmvvm.domain.Model.Provincia
import retrofit2.Call
import retrofit2.http.GET

interface ProvinciaInterface {

    @GET("v1/provincias")
    fun getProvincias(): Call<ArrayList<Provincia>>

}