package com.example.perfiles.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.perfiles.viewmodel.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(viewModel: ProfileViewModel, navController: NavController) {
    val user by viewModel.userProfile.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Información Detallada") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Regresar")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding).padding(16.dp)) {
            item {
                Text(text = "Perfil Personal", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Text(text = user.descripcion, modifier = Modifier.padding(vertical = 8.dp))
                HorizontalDivider()

                Text(text = "Contacto", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(top = 8.dp))
                Text("Edad: ${user.edad} años")
                Text("Ciudad: ${user.ciudad}")
                Text("Correo: ${user.correo}")

                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Hobbies", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                user.hobbies.forEach { Text("• $it") }

                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Deportes", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                user.deportes.forEach { Text("• $it") }
            }
        }
    }
}