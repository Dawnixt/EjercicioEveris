package com.example.weatherappmvvm.presentation.Alert

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import com.example.weatherappmvvm.R

class LoadingDialog(var activity: Activity) {

    lateinit var dialog: AlertDialog
    lateinit var dialogInterface: DialogInterface

    fun startDialog(){

        val inflater: LayoutInflater = activity.layoutInflater
        val layout = inflater.inflate(R.layout.loading_dialog, null)
        var builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        builder.setView(layout)
        builder.setCancelable(false)

        dialog = builder.create()
    }

    fun show(){
        dialog.show()
    }

    fun dismissDialog(){
        dialogInterface.dismiss()
        //dialog.dismiss()
    }

}