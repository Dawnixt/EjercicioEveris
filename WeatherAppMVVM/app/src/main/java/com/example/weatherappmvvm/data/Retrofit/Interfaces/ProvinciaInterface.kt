package com.example.weatherappmvvm.data.retrofit.interfaces

import com.example.weatherappmvvm.domain.model.Provincia
import retrofit2.Call
import retrofit2.http.GET

interface ProvinciaInterface {

    @GET("v1/provincias")
    fun getProvincias(): Call<ArrayList<Provincia>>

}