package com.example.api_students.data.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.api_students.R
import com.example.api_students.data.models.DataEstudiante
import com.example.api_students.databinding.ItemestudianteBinding
import com.squareup.picasso.Picasso

class EstudianteViewHolder (view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemestudianteBinding.bind(view)
    private val btnEditarEs = binding.btnEditarEs
    private val btnDeleteEs = binding.btnDeleteEs

    fun bind(estudiante: DataEstudiante, onEditClickEs: (DataEstudiante) -> Unit, onDeleteClickEs: (DataEstudiante) -> Unit) {
        binding.tvNameestudiante.text = estudiante.nombre
        binding.tvEdadEstudiante.text = estudiante.edad
        binding.tvEmailEstudiante.text = estudiante.email
        binding.tvDireccionEstudiante.text = estudiante.direccion
        binding.tvTelefonoEstudiante.text = estudiante.telefono
        binding.tvCarreraEstudiante.text = estudiante.carrera
        Picasso.get()
            .load(estudiante.imagen)
            .placeholder(R.drawable.baseline_flag_circle_24)
            .error(R.drawable.ic_launcher_foreground)
            .into(binding.ivImage)
        btnEditarEs.setOnClickListener { onEditClickEs(estudiante) }
        btnDeleteEs.setOnClickListener{ onDeleteClickEs(estudiante) }
    }
}