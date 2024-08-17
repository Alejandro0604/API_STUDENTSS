package com.example.api_students.data.models

data class Data(
    val id: Int,
    val nombre: String,
    val image: String,
    val created_at: String? = null,
    val updated_at: String? = null
)
