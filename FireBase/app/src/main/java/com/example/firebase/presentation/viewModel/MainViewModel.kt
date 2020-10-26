package com.example.firebase.presentation.viewModel

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.example.firebase.domain.model.Usuario
import com.example.firebase.presentation.activity.InsertarDatos
import com.google.firebase.auth.FirebaseAuth

class MainViewModel(var activity: Activity): ViewModel() {

    var usuario: Usuario = Usuario("","","")
    var mAuth:FirebaseAuth = FirebaseAuth.getInstance()
    var firstTimeUser: Boolean = true

    fun comprobarUsuarioEstaLogeado(){
        if (mAuth.currentUser != null){
            //Devolover boolean y hacer el start en activity
            val intent: Intent = Intent(activity.baseContext,InsertarDatos::class.java)

        }
    }

    fun crearOLogearUsuario(correo: String, password: String){
        if (correo.isNotBlank() && password.isNotBlank()){
            if (firstTimeUser){
                mAuth.createUserWithEmailAndPassword(correo,password)
            }
            else{
                mAuth.signInWithEmailAndPassword(correo,password)
            }
        }
    }

}