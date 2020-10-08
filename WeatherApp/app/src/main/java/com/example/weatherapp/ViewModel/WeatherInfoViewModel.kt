package com.example.weatherapp.ViewModel

import androidx.lifecycle.*
import com.example.weatherapp.Entities.Municipio
import com.example.weatherapp.Entities.MunicipioTiempo
import com.example.weatherapp.Retrofit.MunicipioCallback
import com.example.weatherapp.Retrofit.MunicipioTiempoCallback

class WeatherInfoViewModel(var lifecycleOwner: LifecycleOwner,var modelOwner: ViewModelStoreOwner): ViewModel() {

    var listaMunicipio: MutableLiveData<ArrayList<Municipio>> = MutableLiveData()
    var mensaje: MutableLiveData<String> = MutableLiveData()
    var idMunicipioSeleccionada: MutableLiveData<String> = MutableLiveData()
    var municipioCallback: MunicipioCallback = MunicipioCallback(lifecycleOwner)
    var municipioTiempoCallback: MunicipioTiempoCallback = MunicipioTiempoCallback(lifecycleOwner)
    var idProvincia: MutableLiveData<String> = MutableLiveData()
    var municipioTiempo: MutableLiveData<MunicipioTiempo> = MutableLiveData()


    /**
     * Esta funcion sirve para cargar los datos de la listad de municipios
     */
    fun obtenerListaMunicipios(){
        municipioCallback.start(modelOwner)
    }

    /**
     * Esta funcion nos permitira obtener el tiempo de un municipio en concreto
     */
    fun obtenerMunicipioConTiempo(){
        municipioTiempoCallback.start(modelOwner)
    }

    fun setMensaje(mensajeNuevo: String) {
        mensaje.value = mensajeNuevo
    }

    fun setIDMunicipioSeleccionada(nuevoIDProvinciaSelecciona: String){
        idMunicipioSeleccionada.value = nuevoIDProvinciaSelecciona
    }

    fun setIDProvinciaSeleccionada(nuevoIDProvincia: String){
        idProvincia.value = nuevoIDProvincia
    }
}

class WeatherInfoFactory(var lifecycleOwner: LifecycleOwner,var modelOwner: ViewModelStoreOwner): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherInfoViewModel(lifecycleOwner,modelOwner) as T
    }
}