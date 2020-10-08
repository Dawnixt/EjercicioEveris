package com.example.weatherapp.Entities

class Hoy {

    var attributes: Attributes = Attributes()
    var estado_cielo: ArrayList<String> = ArrayList()
    var precipitacion: ArrayList<String> = ArrayList()
    //var prob_precipitacion: ArrayList<String> = ArrayList()
    //var prob_tormenta: ArrayList<String> = ArrayList()
    var nieve: ArrayList<String> = ArrayList()
    //var prob_nieve: ArrayList<String> = ArrayList()
    var temperatura: ArrayList<String> = ArrayList()
    var sens_termica: ArrayList<String> = ArrayList()
    var humedad_relativa: ArrayList<String> = ArrayList()
    var viento: ArrayList<Viento> = ArrayList()
    var racha_max: ArrayList<String> = ArrayList()

}