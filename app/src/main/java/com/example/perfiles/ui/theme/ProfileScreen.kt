package com.example.perfiles.ui.theme

import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness4
import androidx.compose.material.icons.filled.Brightness7
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.perfiles.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(viewModel: ProfileViewModel, navController: NavController) {
    val user by viewModel.userProfile.collectAsState()
    val isExpanded by viewModel.isExpanded.collectAsState()
    val seconds by viewModel.secondsRemaining.collectAsState()
    val isDark by viewModel.isDarkTheme.collectAsState()

    val imageSize by animateDpAsState(
        targetValue = if (isExpanded) 100.dp else 200.dp,
        animationSpec = tween(1000),
        label = "SizeAnim"
    )

    Box(modifier = Modifier.fillMaxSize()) {
        IconButton(
            onClick = { viewModel.toggleTheme() },
            modifier = Modifier.align(Alignment.TopEnd).padding(top = 48.dp, end = 16.dp).zIndex(1f)
        ) {
            Icon(
                imageVector = if (isDark) Icons.Default.Brightness7 else Icons.Default.Brightness4,
                contentDescription = "Tema",
                tint = MaterialTheme.colorScheme.primary
            )
        }

        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = if (isExpanded) Arrangement.Top else Arrangement.Center
        ) {
            if (isExpanded) Spacer(modifier = Modifier.height(60.dp))

            AsyncImage(
                model = user.fotoUrl,
                contentDescription = "Foto PNG",
                modifier = Modifier.size(imageSize).clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Text(text = user.nombre, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Text(text = "${user.programa} - Semestre ${user.semestre}")

            if (!isExpanded) {
                Spacer(modifier = Modifier.height(32.dp))
                Box(contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        progress = { seconds / 5f },
                        modifier = Modifier.size(60.dp),
                        strokeWidth = 6.dp
                    )
                    Text(text = "$seconds", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                }
            }

            AnimatedVisibility(
                visible = isExpanded,
                enter = fadeIn() + expandVertically()
            ) {
                LazyColumn(modifier = Modifier.fillMaxWidth().padding(top = 20.dp)) {
                    item {
                        ProfileCardItem("Descripción", user.descripcion)
                        ProfileCardItem("Ciudad", user.ciudad)
                        ProfileCardItem("Contacto", user.correo)

                        Button(
                            onClick = { navController.navigate("details") },
                            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
                        ) {
                            Text("Ver Más Detalles")
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun ProfileCardItem(title: String, content: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = content,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}