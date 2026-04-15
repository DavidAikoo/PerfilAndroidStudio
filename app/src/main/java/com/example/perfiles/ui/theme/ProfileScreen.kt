package com.example.perfiles.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.perfiles.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(viewModel: ProfileViewModel, navController: NavController) {
    val user by viewModel.userProfile.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = user.fotoUrl,
            contentDescription = "Logo Perfil",
            modifier = Modifier.size(180.dp).clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = user.nombre, style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
        Text(text = user.programa, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.secondary)
        Text(text = "Semestre ${user.semestre}", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { navController.navigate("details") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver más información")
        }
    }
}