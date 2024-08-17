package com.example.api_students.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.api_students.R
import com.example.api_students.data.models.DataEstudiante

class EstudianteAdapter (val listaEstudiante: MutableList<DataEstudiante>,
                         private val onEditClickEs: (DataEstudiante) -> Unit,
                         private val onDeleteClickEs: (DataEstudiante) ->Unit): RecyclerView.Adapter<EstudianteViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstudianteViewHolder {
        val binding = LayoutInflater.from(parent.context)
        return EstudianteViewHolder(binding.inflate(R.layout.itemestudiante, parent, false))
    }

    override fun getItemCount(): Int =  listaEstudiante.size

    override fun onBindViewHolder(holder: EstudianteViewHolder, position: Int) {
        val item = listaEstudiante[position]
        holder.bind(item, onEditClickEs, onDeleteClickEs)
    }
}