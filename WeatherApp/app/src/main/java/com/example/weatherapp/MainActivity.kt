package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.Retrofit.ProvinciaCallback
import com.example.weatherapp.ViewModel.MainVM
import com.example.weatherapp.ViewModel.MyFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var vm: MainVM = MainVM()
    var mf: MyFactory = MyFactory()
    var provinciaCallback: ProvinciaCallback = ProvinciaCallback()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vm = ViewModelProvider(this,mf).get(MainVM::class.java)

        vm.listaProvincia.observe(this){

            TVPruebas.text = it[0].toString()

        }

        vm.mensaje.observe(this){
            TVPruebas.text = it
        }

        provinciaCallback.start(this)

    }
}