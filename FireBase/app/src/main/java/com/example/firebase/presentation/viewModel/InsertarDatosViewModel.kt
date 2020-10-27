package com.example.firebase.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.example.firebase.domain.model.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

//https://console.firebase.google.com/project/mi-prueba-266008/authentication/providers
class InsertarDatosViewModel(): ViewModel() {

    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    var rootNode: FirebaseDatabase = FirebaseDatabase.getInstance()
    var usuario:Usuario = Usuario("","","")
    lateinit var reference: DatabaseReference

    fun logOut(){
        mAuth.signOut()
    }

    fun saveData(){
        reference = rootNode.getReference("Usuarios")
        reference.child(usuario.phone).setValue(usuario)
    }

}