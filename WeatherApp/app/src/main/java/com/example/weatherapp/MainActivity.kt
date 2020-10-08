package com.example.weatherapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.Lista.ProvinciaRecyclerAdapter
import com.example.weatherapp.Retrofit.ProvinciaCallback
import com.example.weatherapp.ViewModel.MainViewModel
import com.example.weatherapp.ViewModel.MyFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var viewModel: MainViewModel = MainViewModel()
    var myFactory: MyFactory = MyFactory()
    var recyclerAdapter: ProvinciaRecyclerAdapter? = null
    var provinciaCallback: ProvinciaCallback = ProvinciaCallback()
    var PERMISSION_ACCESS_COARSE_LOCATION: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("daniel", "MainActivity.onCreate()")

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),PERMISSION_ACCESS_COARSE_LOCATION)
        }

        viewModel = ViewModelProvider(this,myFactory).get(MainViewModel::class.java)

        //Observo la lista de provincias para que cuando lleguen los datos se cree un nuevo adaptor
        viewModel.listaProvincia.observe(this){
            recyclerAdapter = ProvinciaRecyclerAdapter(it,this,myFactory)
            listaProvincias.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = recyclerAdapter
            }
        }

        //Observo cuando cambia el id seleccionado porque eso significa que ya ha selecionado una provincia
        viewModel.idProvinciaSeleccionada.observe(this){
            var intentMunicipio = Intent(this,WeatherInfoActivity::class.java)
            intentMunicipio.putExtra("idProvincia", it)
            startActivity(intentMunicipio)
        }

        //Si ocurre algun error lo mostrare por la pantalla
        viewModel.mensaje.observe(this){
            Toast.makeText(this,viewModel.mensaje.value,Toast.LENGTH_LONG).show()
        }

        provinciaCallback.start(this)

    }

    override fun onRequestPermissionsResult(requestCode: Int,permissions: Array<out String>,grantResults: IntArray) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode){
            PERMISSION_ACCESS_COARSE_LOCATION ->
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                }
                else{
                    Toast.makeText(this,"Necesitamos tu localizacion",Toast.LENGTH_SHORT).show()
                }
            //Aqui para el interneto pero primero quiero ver si la localizacion
        }
    }

}