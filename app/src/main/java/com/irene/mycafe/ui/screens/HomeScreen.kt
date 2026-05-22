package com.irene.mycafe.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.irene.mycafe.data.PreferenceManager
import com.irene.mycafe.data.menuList
import com.irene.mycafe.ui.Screen
import com.irene.mycafe.ui.theme.NatureGreen
import com.irene.mycafe.ui.theme.NatureWhite

@Composable
fun HomeScreen(
    navController: NavController, 
    preferenceManager: PreferenceManager,
    isDarkMode: MutableState<Boolean>
) {
    val profile = preferenceManager.getProfile()
    val carouselItems = remember { menuList.shuffled().take(5) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Eco,
                    contentDescription = null,
                    tint = NatureGreen,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = profile.name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = NatureGreen
                    )
                    Text(
                        text = "Healthy Canteen",
                        fontSize = 12.sp,
                        color = NatureGreen
                    )
                }
            }
            IconButton(onClick = { 
                val newValue = !isDarkMode.value
                isDarkMode.value = newValue
                preferenceManager.setDarkMode(newValue)
            }) {
                Icon(
                    imageVector = if (isDarkMode.value) Icons.Default.LightMode else Icons.Default.DarkMode, 
                    contentDescription = null, 
                    tint = NatureGreen
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Carousel
        Text(
            text = "Menu Rekomendasi",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(carouselItems) { item ->
                Card(
                    modifier = Modifier
                        .width(280.dp)
                        .height(150.dp)
                        .clickable { navController.navigate(Screen.DetailMenu.createRoute(item.id)) },
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Box {
                        AsyncImage(
                            model = item.imageUrl,
                            contentDescription = item.name,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Black.copy(alpha = 0.3f))
                        )
                        Text(
                            text = item.name,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(16.dp),
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }

        Text(
            text = "Halo, Pelanggan!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Ringkasan hari ini",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Stats Grid
        Row(modifier = Modifier.fillMaxWidth()) {
            StatCard(title = "Menu Sehat", value = menuList.size.toString(), modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(16.dp))
            StatCard(title = "Ulasan", value = "4.9", modifier = Modifier.weight(1f))
        }
        
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { navController.navigate(Screen.Menu.route) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = NatureGreen),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Lihat Menu", color = Color.White, modifier = Modifier.padding(8.dp))
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedButton(
            onClick = { navController.navigate(Screen.Profile.route) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = NatureGreen),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Profil Restoran", modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun StatCard(title: String, value: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.height(100.dp),
        colors = CardDefaults.cardColors(containerColor = NatureGreen.copy(alpha = 0.8f)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(title, color = Color.White, fontSize = 12.sp)
            Text(value, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}
