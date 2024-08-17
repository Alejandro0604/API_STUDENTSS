package com.example.api_students.data.models

data class DataEstudiante(
    val id: Int,
    val nombre: String,
    val edad: String,
    val email: String,
    val direccion: String,
    val telefono: String,
    val carrera: String,
    val imagen: String,
    val created_at: String? = null,
    val updated_at: String? = null
)