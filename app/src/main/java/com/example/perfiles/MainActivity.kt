package com.example.perfiles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.perfiles.ui.DetailsScreen
// CAMBIO AQUÍ: Ahora importamos ProfileScreen desde el paquete theme
import com.example.perfiles.ui.theme.ProfileScreen
import com.example.perfiles.ui.theme.PerfilesTheme
import com.example.perfiles.viewmodel.ProfileViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            val isDarkTheme by viewModel.isDarkTheme.collectAsState()

            PerfilesTheme(darkTheme = isDarkTheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "profile"
                    ) {
                        composable("profile") {
                            ProfileScreen(viewModel, navController)
                        }
                        composable("details") {
                            DetailsScreen(viewModel, navController)
                        }
                    }
                }
            }
        }
    }
}