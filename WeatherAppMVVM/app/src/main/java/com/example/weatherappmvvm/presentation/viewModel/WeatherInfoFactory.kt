package com.example.weatherappmvvm.presentation.viewModel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class WeatherInfoFactory(var lifecycleOwner: LifecycleOwner): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherInfoViewModel(lifecycleOwner) as T
    }
}