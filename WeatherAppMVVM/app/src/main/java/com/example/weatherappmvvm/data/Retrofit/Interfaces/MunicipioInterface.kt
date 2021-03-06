package com.example.weatherappmvvm.data.retrofit.interfaces

import com.example.weatherappmvvm.domain.model.Municipios
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MunicipioInterface {
    @GET("v2/provincias/{idProvincia}/municipios")
    fun getMunicipios(@Path("idProvincia") idProvincia: String ): Call<Municipios>
}