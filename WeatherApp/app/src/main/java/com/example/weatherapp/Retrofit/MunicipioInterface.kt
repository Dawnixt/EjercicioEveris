package com.example.weatherapp.Retrofit

import retrofit2.Call
import com.example.weatherapp.Entities.Municipio
import com.example.weatherapp.Entities.MunicipioTiempo
import com.example.weatherapp.Entities.Municipios
import retrofit2.http.GET
import retrofit2.http.Path

interface MunicipioInterface {

    @GET("v2/provincias/{idProvincia}/municipios")
    fun getMunicipios(@Path("idProvincia") idProvincia: String ): Call<Municipios>

    @GET("v2/provincias/{idProvincia}/municipios/{idMunicipio}") //Cod de municipio es CODIGOINE pero solo los 5 primeros digitos un poco raro si me preguntan pero bueno
    fun getTiempoMunicipio(@Path("idProvincia") idProvincia: String, @Path("idMunicipio") idMunicipio: String) : Call<MunicipioTiempo>
}