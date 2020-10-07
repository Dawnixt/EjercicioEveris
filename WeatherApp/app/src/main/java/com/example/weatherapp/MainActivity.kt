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

class MainActivity : AppCompatActivity(), View.OnClickListener {

    //https://www.el-tiempo.net/api/json/v2/provincias/02/municipios/02070
    //https://www.el-tiempo.net/api/json/v1/provincias/02/municipios
    //https://www.el-tiempo.net/api/json/v2/municipios
    //https://www.el-tiempo.net/api
    //https://www.el-tiempo.net/api/json/v1/provincias
    //https://www.el-tiempo.net/api/json/v2/home
    //https://www.el-tiempo.net/api/json/v2/provincias
    //https://www.el-tiempo.net/api/json/v2/provincias/02/municipios
    //https://www.el-tiempo.net/api/json/v2/provincias/02/municipios/
    //https://www.color-hex.com/color/44eded
    //https://antonioleiva.com/recyclerview-listener/

    //Mejor poner los nombres largos
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

        // TODO Mostrarlo como un mensajito con Toast
        viewModel.mensaje.observe(this){
            TVPruebas.text = it
        }

        //TODO Quitar
        BTNMunicipio.setOnClickListener(this)
        //Buscar otra forma o sino tendria que implementar el que la lista se mueva
        //listaProvincias.addOnItemTouchListener(this)

        //Mover al ViewModel
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

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.BTNMunicipio ->
                {var intentMunicipio = Intent(this,WeatherInfoActivity::class.java)
                startActivity(intentMunicipio)}
        }

    }


}