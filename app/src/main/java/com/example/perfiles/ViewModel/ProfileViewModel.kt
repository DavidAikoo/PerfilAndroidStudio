package com.example.perfiles.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perfiles.model.UserProfile
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

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
            fotoUrl = "https://i.pinimg.com/736x/3b/ae/f3/3baef3d4b9121d7ce068d02136df59a7.jpg"
        )
    )
    val userProfile: StateFlow<UserProfile> = _userProfile.asStateFlow()

    private val _isDarkTheme = MutableStateFlow(false)
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme.asStateFlow()

    private val _secondsRemaining = MutableStateFlow(5)
    val secondsRemaining: StateFlow<Int> = _secondsRemaining.asStateFlow()

    private val _isExpanded = MutableStateFlow(false)
    val isExpanded: StateFlow<Boolean> = _isExpanded.asStateFlow()

    init {
        startTimer()
    }

    private fun startTimer() {
        viewModelScope.launch {
            while (_secondsRemaining.value > 0) {
                delay(1000)
                _secondsRemaining.value -= 1
            }
            _isExpanded.value = true
        }
    }

    fun toggleTheme() {
        _isDarkTheme.value = !_isDarkTheme.value
    }
}