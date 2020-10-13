package com.example.weatherappmvvm.domain.ViewModel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.example.weatherappmvvm.data.Retrofit.ProvinciaCallback

class MainViewModel(var owner: LifecycleOwner): ViewModel() {

    val provinciaCallback: ProvinciaCallback = ProvinciaCallback()

    fun getCosas(){
        //Observe ha cambiado ahora me odia porque? :(
        provinciaCallback.mensaje.observe(owner){}
    }

}

private fun <T> MutableLiveData<T>.observe(owner: LifecycleOwner, function: () -> Unit) {
    
}
