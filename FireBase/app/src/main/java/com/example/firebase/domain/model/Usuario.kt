package com.example.firebase.domain.model

class Usuario {

    var nombre:String = ""
    var edad = ""
    var phone = ""

    constructor( nombre: String, edad: String, phone: String){
        this.nombre = nombre
        this.edad = edad
        this.phone = phone
    }

    constructor(){

    }

}