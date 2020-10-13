package com.example.weatherappmvvm.data.Retrofit

import androidx.lifecycle.LifecycleOwner
import com.example.weatherappmvvm.domain.Model.Municipios
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MunicipioCallback(var lifeowner: LifecycleOwner): Callback<Municipios> {
    override fun onFailure(call: Call<Municipios>, t: Throwable) {
        TODO("Not yet implemented")
    }

    override fun onResponse(call: Call<Municipios>, response: Response<Municipios>) {
        TODO("Not yet implemented")
    }
}