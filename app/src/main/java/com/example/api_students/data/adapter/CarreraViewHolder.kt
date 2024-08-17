package com.example.api_students.data.adapter

import android.view.View
import android.widget.Toast
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.api_students.R
import com.example.api_students.data.models.Data
import com.example.api_students.databinding.ItemcarreraBinding
import com.squareup.picasso.Picasso

class CarreraViewHolder (view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemcarreraBinding.bind(view)
    private val btnEdit = binding.btnEdit
    private val btnDelete = binding.btnDel

    fun bind(carrera: Data, onEditClick: (Data) -> Unit, onDeleteClick: (Data) -> Unit) {
        binding.tvNameCarrera.text = carrera.nombre
        Picasso.get()
            .load(carrera.image)
            .placeholder(R.drawable.baseline_flag_circle_24)
            .error(R.drawable.ic_launcher_foreground)
            .into(binding.ivImage)
        btnEdit.setOnClickListener { onEditClick(carrera) }
        btnDelete.setOnClickListener { onDeleteClick(carrera) }

    }
}