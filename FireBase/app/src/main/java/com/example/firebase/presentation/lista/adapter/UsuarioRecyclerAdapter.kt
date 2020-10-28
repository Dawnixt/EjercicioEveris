package com.example.firebase.presentation.lista.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.R
import com.example.firebase.domain.model.Usuario
import com.example.firebase.presentation.lista.viewholder.UsuarioViewHolder

class UsuarioRecyclerAdapter(var usuarios: ArrayList<Usuario>):RecyclerView.Adapter<UsuarioViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        return UsuarioViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.viewholder,parent,false))
    }

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        holder.bind(usuarios[position])
    }

    override fun getItemCount(): Int {
        return usuarios.size
    }
}