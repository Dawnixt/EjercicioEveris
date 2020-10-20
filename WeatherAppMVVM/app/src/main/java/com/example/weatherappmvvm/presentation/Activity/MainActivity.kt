package com.example.weatherappmvvm.presentation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherappmvvm.R
import com.example.weatherappmvvm.presentation.lista.adapter.ProvinciaRecyclerAdapter
import com.example.weatherappmvvm.domain.model.Provincia
import com.example.weatherappmvvm.presentation.viewModel.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mainViewModel: MainViewModel = MainViewModel(this)
    var myFactory: MyFactory = MyFactory(this)
    var recyclerAdapter: ProvinciaRecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("daniel", "MainActivity.onCreate()")

        mainViewModel = ViewModelProvider(this,myFactory).get(MainViewModel::class.java)

        //Observo la lista de provincias y creo la lista cuando llegan los datos
        mainViewModel.listadoProvincias.observe(this,object: Observer<ArrayList<Provincia>>{
            override fun onChanged(t: ArrayList<Provincia>?) {
                recyclerAdapter = ProvinciaRecyclerAdapter(this@MainActivity,t ?: ArrayList(),this@MainActivity,myFactory)
                listaProvincias.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = recyclerAdapter
                }
            }
        })

        //Observo por si salta algun error y lo muestro por pantalla
        mainViewModel.mensaje.observe(this,object: Observer<String>{
            override fun onChanged(t: String?) {
                Toast.makeText(this@MainActivity,mainViewModel.mensaje.value,Toast.LENGTH_LONG).show()
            }
        })

        //Observo para cuando selecciona una provincia mandar al ususario a la otra actividad
        mainViewModel.idProvinciaSeleccionada.observe(this,object: Observer<String>{
            override fun onChanged(t: String?) {
                val intentMunicipio = Intent(this@MainActivity,WeatherInfoActivity::class.java)
                intentMunicipio.putExtra("idProvincia", t)
                startActivity(intentMunicipio)
            }
        })

        mainViewModel.getListadoProvincias()

    }
}