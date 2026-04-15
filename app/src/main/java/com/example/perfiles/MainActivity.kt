package com.example.perfiles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.perfiles.ui.DetailsScreen
import com.example.perfiles.ui.ProfileScreen
import com.example.perfiles.ui.theme.PerfilesTheme
import com.example.perfiles.viewmodel.ProfileViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PerfilesTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "profile") {
                    composable("profile") { ProfileScreen(viewModel, navController) }
                    composable("details") { DetailsScreen(viewModel, navController) }
                }
            }
        }
    }
}