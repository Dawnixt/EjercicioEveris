package com.example.firebase.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firebase.domain.model.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

//https://console.firebase.google.com/project/mi-prueba-266008/authentication/providers
class InsertarDatosViewModel(): ViewModel() {

    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    var rootNode: FirebaseDatabase = FirebaseDatabase.getInstance()
    var usuario:Usuario = Usuario("","","")
    //var aux:Usuario = Usuario("","","")
    var listaUsuarios: MutableLiveData<ArrayList<Usuario>> = MutableLiveData()
    lateinit var reference: DatabaseReference

    fun logOut(){
        mAuth.signOut()
    }

    fun saveData(){
        reference = rootNode.getReference("Usuarios")
        reference.child(usuario.phone).setValue(usuario)
    }

    fun DataRetriever(){
        var aux: ArrayList<Usuario> = ArrayList()
        //reference = rootNode.getReference("Usuarios")
        reference.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()){
                    for (i in p0.children){
                        aux.add(i.getValue(Usuario::class.java) ?:Usuario("","","") )
                    }
                    listaUsuarios.value = aux
                }
            }
            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }

}