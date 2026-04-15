package com.example.perfiles.viewmodel

import androidx.lifecycle.ViewModel
import com.example.perfiles.model.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel : ViewModel() {
    private val _userProfile = MutableStateFlow(
        UserProfile(
            nombre = "Juan David Ruge Garzón",
            programa = "Ingeniería de Software",
            semestre = 5,
            descripcion = "Estudiante apasionado por la configuración y desarrollo de plugins y servidores de minecraft.",
            edad = 19,
            ciudad = "Chía, Cundinamarca",
            correo = "jdavidruge@ucundinamarca.edu.co",
            hobbies = listOf("Programación en React/Node", "Gestión de servidores Minecraft"),
            pasatiempos = listOf("Diseño de assets voxel", "Comunidades Crypto"),
            deportes = listOf("Basketball"),
            intereses = listOf("VideoJuegos", "Blockchain"),
            fotoUrl = "https://thumbs.dreamstime.com/b/icono-de-enlace-roto-que-representa-la-desconexi%C3%B3n-433369088.jpg?w=768"
        )
    )
    val userProfile: StateFlow<UserProfile> = _userProfile
}