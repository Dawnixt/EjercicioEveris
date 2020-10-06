package com.example.weatherapp.Retrofit

import retrofit2.Call
import com.example.weatherapp.Entities.Municipio
import retrofit2.http.GET
import retrofit2.http.Path

interface MunicipioInterface {

    @GET("v1/provincias/{idProvincia}/municipios")
    fun getMunicipios(@Path("idProvincia") idProvincia: String ): Call<ArrayList<Municipio>>
}