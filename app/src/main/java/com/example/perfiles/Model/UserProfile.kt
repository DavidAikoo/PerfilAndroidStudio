package com.example.perfiles.model

data class UserProfile(
    val nombre: String,
    val programa: String,
    val semestre: Int,
    val descripcion: String,
    val edad: Int,
    val ciudad: String,
    val correo: String,
    val hobbies: List<String>,
    val pasatiempos: List<String>,
    val deportes: List<String>,
    val intereses: List<String>,
    val fotoUrl: String
)