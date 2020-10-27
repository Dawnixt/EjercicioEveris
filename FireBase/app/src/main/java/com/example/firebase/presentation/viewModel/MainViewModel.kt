package com.example.firebase.presentation.viewModel

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException

class MainViewModel(var activity: Activity): ViewModel() {

    //var usuario: Usuario = Usuario("","","")
    var mAuth:FirebaseAuth = FirebaseAuth.getInstance()
    var firstTimeUser: Boolean = true
    var successful: MutableLiveData<Boolean> = MutableLiveData()

    fun comprobarUsuarioEstaLogeado(): Boolean{
        var logeado: Boolean = false

        if (mAuth.currentUser != null){
            logeado = true
        }

        return logeado
    }

    fun crearOLogearUsuario(correo: String, password: String){
        //Observar
        if (correo.isNotBlank() && password.isNotBlank()){
            if (firstTimeUser){
                mAuth.createUserWithEmailAndPassword(correo,password)
            }
            else{
                mAuth.signInWithEmailAndPassword(correo,password).addOnCompleteListener(activity){
                    successful.value = it.isSuccessful
                }
            }
        }
    }

}