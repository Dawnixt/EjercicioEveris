package com.example.weatherappmvvm.domain.model

class MunicipioTiempo {

    var municipio: Municipio = Municipio()
    var fecha = ""
    var stateSky: StateSky = StateSky()
    var temperatura_actual = ""
    var temperaturas: Temperaturas = Temperaturas()
    var humedad = ""
    var viento = ""
    var lluvia = ""
    //imagen
    var pronostico: Pronostico = Pronostico()
    //var proximos_dias: ArrayList<ProximosDias> = ArrayList()
}