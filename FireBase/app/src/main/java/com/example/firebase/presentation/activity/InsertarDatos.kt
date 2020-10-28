package com.example.firebase.presentation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebase.R
import com.example.firebase.domain.model.Usuario
import com.example.firebase.presentation.lista.adapter.UsuarioRecyclerAdapter
import com.example.firebase.presentation.viewModel.InsertarDatosFactory
import com.example.firebase.presentation.viewModel.InsertarDatosViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_insertar_datos.*

class InsertarDatos : AppCompatActivity(), View.OnClickListener {

    var insertarDatosViewModel: InsertarDatosViewModel = InsertarDatosViewModel()
    var insertarDatosFactory: InsertarDatosFactory = InsertarDatosFactory()
    var listaUsuariosAdapter: UsuarioRecyclerAdapter? = null
    //lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertar_datos)

        insertarDatosViewModel = ViewModelProvider(this,insertarDatosFactory).get(InsertarDatosViewModel::class.java)
        insertarDatosViewModel.mAuth = FirebaseAuth.getInstance()
        //listaUsuariosAdapter = UsuarioRecyclerAdapter()

        btnLogOut.setOnClickListener(this)
        btnInsertar.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnLogOut->{
                insertarDatosViewModel.logOut()
                val intent: Intent = Intent(this,MainActivity::class.java)
                startActivity(intent)}
            R.id.btnInsertar->{
                var usuario = Usuario(editTextNombre.text.toString(),editTextAge.text.toString(),editTextPhone.text.toString())
                insertarDatosViewModel.usuario = usuario
                insertarDatosViewModel.saveData()
                insertarDatosViewModel.DataRetriever()

                insertarDatosViewModel.listaUsuarios.observe(this,){
                    listaUsuariosAdapter = UsuarioRecyclerAdapter(it)
                    recycleView.apply {
                        layoutManager = LinearLayoutManager(this@InsertarDatos)
                        adapter = listaUsuariosAdapter
                    }
                }
            }
        }
    }
}