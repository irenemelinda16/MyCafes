package com.irene.mycafe.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
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
import com.irene.mycafe.data.CartManager
import com.irene.mycafe.data.menuList
import com.irene.mycafe.ui.theme.NatureGreen
import com.irene.mycafe.ui.theme.NatureWhite
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailMenuScreen(navController: NavController, menuId: String?) {
    val item = menuList.find { it.id == menuId } ?: return
    var rating by remember { mutableIntStateOf(item.userRating) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail Menu", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = NatureGreen,
                    navigationIconContentColor = NatureGreen
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            AsyncImage(
                model = item.imageUrl,
                contentDescription = item.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .padding(24.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item.name,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.weight(1f)
                    )
                }

                // Interactive Rating
                Row(
                    modifier = Modifier.padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(5) { index ->
                        val currentStar = index + 1
                        Icon(
                            imageVector = if (currentStar <= rating) Icons.Default.Star else Icons.Default.StarBorder,
                            contentDescription = null,
                            tint = if (currentStar <= rating) Color(0xFFFFC107) else Color.Gray,
                            modifier = Modifier
                                .size(32.dp)
                                .clickable { 
                                    rating = currentStar
                                    item.userRating = currentStar
                                }
                        )
                    }
                    Text(
                        text = if (rating > 0) "($rating/5)" else "(Belum dirating)",
                        modifier = Modifier.padding(start = 8.dp),
                        color = Color.Gray
                    )
                }

                Text(
                    text = item.category,
                    fontSize = 16.sp,
                    color = NatureGreen,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Text(
                    text = "Rp ${String.format(Locale.getDefault(), "%,d", item.price)}",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = NatureGreen
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Deskripsi",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = item.description,
                    fontSize = 16.sp,
                    color = Color.Gray,
                    lineHeight = 24.sp
                )

                Spacer(modifier = Modifier.height(40.dp))

                Button(
                    onClick = {
                        CartManager.addToCart(item)
                        navController.navigate(com.irene.mycafe.ui.Screen.Cart.route)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = NatureGreen),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Tambah ke Keranjang", color = Color.White, modifier = Modifier.padding(8.dp))
                }

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = NatureGreen),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Kembali ke Menu", modifier = Modifier.padding(8.dp))
                }
            }
        }
    }
}
