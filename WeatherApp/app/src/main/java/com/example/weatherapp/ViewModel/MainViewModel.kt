package com.example.weatherapp.ViewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.Entities.Municipio
import com.example.weatherapp.Entities.Provincia

class MainViewModel: ViewModel() {

    var listaProvincia: MutableLiveData<ArrayList<Provincia>> = MutableLiveData()
    var mensaje: MutableLiveData<String> = MutableLiveData()
    var idProvinciaSeleccionada: MutableLiveData<String> = MutableLiveData()
    var listaMunicipio: MutableLiveData<ArrayList<Municipio>> = MutableLiveData()

    fun setMensaje(mensajeNuevo: String) {
        mensaje.value = mensajeNuevo
    }

    fun setIDProvinciaSeleccionada(nuevoIDProvinciaSelecciona: String){
        idProvinciaSeleccionada.value = nuevoIDProvinciaSelecciona
    }
}

class MyFactory(): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel() as T
    }
}