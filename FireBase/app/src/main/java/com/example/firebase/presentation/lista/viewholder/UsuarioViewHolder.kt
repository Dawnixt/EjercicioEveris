package com.example.firebase.presentation.lista.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.domain.model.Usuario
import kotlinx.android.synthetic.main.viewholder.view.*

class UsuarioViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {

    var textViewNombre = view.textViewNombre

    fun bind(row: Usuario){
        textViewNombre.text = row.nombre
    }

    override fun onClick(v: View?) {

    }
}