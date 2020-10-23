package com.example.firebase.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.firebase.R

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var prueba = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Comprobar si el usuario ya esta registrado FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){
                R.id.btnSignIn->  prueba = "In"//Comprobar si existe en firebase
                R.id.btnSignUp-> prueba = "Up //Crear usuario"

            }
        }
    }

}