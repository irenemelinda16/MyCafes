package com.irene.mycafe.ui.screens

import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.irene.mycafe.data.CartItem
import com.irene.mycafe.data.CartManager
import com.irene.mycafe.ui.theme.NatureGreen
import com.irene.mycafe.ui.theme.NatureWhite
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(navController: NavController) {
    val context = LocalContext.current
    val cartItems = CartManager.items

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Keranjang Belanja", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = NatureGreen,
                    navigationIconContentColor = NatureGreen
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        if (cartItems.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(innerPadding), contentAlignment = Alignment.Center) {
                Text("Keranjang Anda kosong", color = Color.Gray)
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                LazyColumn(
                    modifier = Modifier.weight(1f).padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp)
                ) {
                    items(cartItems, key = { it.menuItem.id }) { item ->
                        AnimatedVisibility(
                            visible = true,
                            enter = fadeIn() + expandVertically(),
                            exit = fadeOut() + shrinkVertically()
                        ) {
                            CartItemCard(item)
                        }
                    }
                }

                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.surface,
                    shadowElevation = 8.dp
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Total Pembayaran", fontSize = 16.sp, color = MaterialTheme.colorScheme.onSurface)
                            Text(
                                "Rp ${String.format(Locale.getDefault(), "%,d", CartManager.getTotalPrice())}",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = NatureGreen
                            )
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                        Button(
                            onClick = {
                                Toast.makeText(context, "Pesanan Berhasil! Terima kasih.", Toast.LENGTH_LONG).show()
                                CartManager.clearCart()
                                navController.popBackStack()
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = NatureGreen),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("Checkout Sekarang", color = Color.White, modifier = Modifier.padding(8.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CartItemCard(item: CartItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = item.menuItem.imageUrl,
                contentDescription = item.menuItem.name,
                modifier = Modifier.size(70.dp).clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            
            Spacer(modifier = Modifier.width(12.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(item.menuItem.name, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = MaterialTheme.colorScheme.onSurface)
                Text("Rp ${String.format(Locale.getDefault(), "%,d", item.menuItem.price)}", color = NatureGreen, fontSize = 14.sp)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { CartManager.removeFromCart(item.menuItem) }) {
                    Icon(Icons.Default.Remove, contentDescription = null, tint = NatureGreen)
                }
                Text("${item.quantity}", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                IconButton(onClick = { CartManager.addToCart(item.menuItem) }) {
                    Icon(Icons.Default.Add, contentDescription = null, tint = NatureGreen)
                }
            }
        }
    }
}
