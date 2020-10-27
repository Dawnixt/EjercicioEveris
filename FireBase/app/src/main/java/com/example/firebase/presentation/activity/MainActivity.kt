package com.example.firebase.presentation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.firebase.R
import com.example.firebase.presentation.viewModel.MainViewModel
import com.example.firebase.presentation.viewModel.MyFactory
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
//https://www.youtube.com/watch?v=eGWu0-0TWFI&list=PL5jb9EteFAOCO_uRl2--aQ-0d8r01QjS-
class MainActivity : AppCompatActivity(), View.OnClickListener {

    var mainViewModel: MainViewModel = MainViewModel(this)
    var myFactory: MyFactory = MyFactory(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this,myFactory).get(MainViewModel::class.java)
        btnSignIn.setOnClickListener(this)
        btnSignUp.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){
                R.id.btnSignIn->
                    mainViewModel.firstTimeUser = false

                R.id.btnSignUp->
                    mainViewModel.firstTimeUser = true
            }
            mainViewModel.crearOLogearUsuario(editTextCorreo.text.toString(),editTextContrasenia.text.toString())
            mainViewModel.successful.observe(this){
                if (it){
                    Toast.makeText(this,"Te has logeado",Toast.LENGTH_SHORT).show()
                    val intent: Intent = Intent(this,InsertarDatos::class.java)
                    startActivity(intent)
                }
                else Toast.makeText(this,"No te has logeado",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (mainViewModel.comprobarUsuarioEstaLogeado()){
            val intent: Intent = Intent(this,InsertarDatos::class.java)
            startActivity(intent)
        }
    }

}