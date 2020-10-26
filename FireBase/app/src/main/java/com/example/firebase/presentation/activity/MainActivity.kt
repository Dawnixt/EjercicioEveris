package com.example.firebase.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.firebase.R
import com.example.firebase.presentation.viewModel.MainViewModel
import com.example.firebase.presentation.viewModel.MyFactory
import kotlinx.android.synthetic.main.activity_main.*

//https://www.youtube.com/watch?v=HdXEzuaka5Y

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var mainViewModel: MainViewModel = MainViewModel(this)
    var myFactory: MyFactory = MyFactory(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Comprobar si el usuario ya esta registrado FirebaseUser currentUser = mAuth.getCurrentUser();
        mainViewModel = ViewModelProvider(this,myFactory).get(MainViewModel::class.java)
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

            //Hacer el intent a la otra Activity aqui
        }
    }

}