package com.example.weatherappmvvm.presentation.Alert

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater
import com.example.weatherappmvvm.R

class LoadingDialog(var activity: Activity) {

    lateinit var dialog: AlertDialog

    fun startDialog(){
        var builder: AlertDialog.Builder = AlertDialog.Builder(activity)

        val inflater: LayoutInflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.loading_dialog, null))
        builder.setCancelable(false)

        dialog = builder.create()
    }

    fun show(){
        dialog.show()
    }

    fun dismissDialog(){
        dialog.dismiss()
    }

}