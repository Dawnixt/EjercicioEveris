package com.example.weatherapp.Retrofit

import com.example.weatherapp.Entities.Provincia
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Call

interface ProvinciaInterface {

    @GET("v1/provincias")
    fun getProvincias(): Call<ArrayList<Provincia>>



}