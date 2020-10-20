package com.example.weatherappmvvm.presentation.lista.viewHolder

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherappmvvm.domain.model.Provincia
import com.example.weatherappmvvm.presentation.viewModel.MainViewModel
import com.example.weatherappmvvm.presentation.viewModel.MyFactory
import kotlinx.android.synthetic.main.viewholder.view.*

class ProvinciaViewHolder(owner: LifecycleOwner, view: View, storeOwner: ViewModelStoreOwner, viewModelFactory: MyFactory): RecyclerView.ViewHolder(view), View.OnClickListener{

    var mainViewModel: MainViewModel = MainViewModel(owner)

    init {
        view.setOnClickListener(this)
        mainViewModel = ViewModelProvider(storeOwner,viewModelFactory).get(MainViewModel::class.java)

    }

    var textViewNombreProvincia = view.TextViewNombre

    fun bind(row: Provincia){
        textViewNombreProvincia.text = row.NOMBRE_PROVINCIA
    }

    override fun onClick(v: View?) {

        mainViewModel.idProvinciaSeleccionada.value = mainViewModel.listadoProvincias.value?.get(adapterPosition)?.CODPROV

    }

}