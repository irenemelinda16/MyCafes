package com.irene.mycafe.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.irene.mycafe.ui.Screen
import com.irene.mycafe.ui.theme.NatureGreen

@Composable
fun BottomNavBar(navController: NavController) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    Surface(
        color = NatureGreen, // Changed to Green background
        shadowElevation = 8.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavItem(
                icon = Icons.Default.Home,
                label = "Home",
                isSelected = currentRoute == Screen.Home.route,
                onClick = {
                    if (currentRoute != Screen.Home.route) {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Home.route) { inclusive = true }
                        }
                    }
                }
            )
            NavItem(
                icon = Icons.AutoMirrored.Filled.List,
                label = "Menu",
                isSelected = currentRoute == Screen.Menu.route,
                onClick = {
                    if (currentRoute != Screen.Menu.route) {
                        navController.navigate(Screen.Menu.route)
                    }
                }
            )

            NavItem(
                icon = Icons.Default.ShoppingCart,
                label = "Cart",
                isSelected = currentRoute == Screen.Cart.route,
                onClick = {
                    if (currentRoute != Screen.Cart.route) {
                        navController.navigate(Screen.Cart.route)
                    }
                }
            )

            NavItem(
                icon = Icons.Default.Person,
                label = "Profile",
                isSelected = currentRoute == Screen.Profile.route,
                onClick = {
                    if (currentRoute != Screen.Profile.route) {
                        navController.navigate(Screen.Profile.route)
                    }
                }
            )
        }
    }
}

@Composable
fun NavItem(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val contentColor = if (isSelected) Color.White else Color.White.copy(alpha = 0.6f)
    
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = contentColor,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = label,
            fontSize = 10.sp,
            color = contentColor
        )
    }
}
