package com.example.weatherapp.ViewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.Entities.Provincia

class MainVM: ViewModel() {

    var listaProvincia: MutableLiveData<ArrayList<Provincia>> = MutableLiveData()
    var mensaje: MutableLiveData<String> = MutableLiveData()

    fun setMensaje(mensajeNuevo: String) {
        mensaje.value = mensajeNuevo
    }
}

class MyFactory(): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainVM() as T
    }
}