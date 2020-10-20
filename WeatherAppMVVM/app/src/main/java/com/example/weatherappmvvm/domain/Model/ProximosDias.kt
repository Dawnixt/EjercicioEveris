package com.example.weatherappmvvm.domain.model

class ProximosDias {

    var attribute: AttributesDias = AttributesDias()
    //var prob_precipitacion: ArrayList<String> = ArrayList()
    //var cota_nieve_prov: ArrayList<CotaNieve> = ArrayList()
    //var estado_cielo: ArrayList<String> = ArrayList()
    //var viento: ArrayList<Viento> = ArrayList()
    //racha_max
    var temperatura: Temperatura = Temperatura()
    var sens_termica: Temperatura = Temperatura()
    var humedad_relativa: Temperatura = Temperatura()
    var uv_max = ""

}