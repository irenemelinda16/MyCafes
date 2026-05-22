package com.irene.mycafe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.irene.mycafe.data.PreferenceManager
import com.irene.mycafe.ui.Screen
import com.irene.mycafe.ui.components.BottomNavBar
import com.irene.mycafe.ui.screens.*
import com.irene.mycafe.ui.theme.MyCafeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            val preferenceManager = remember { PreferenceManager(context) }
            val isDarkMode = remember { mutableStateOf(preferenceManager.isDarkMode()) }
            
            MyCafeTheme(darkTheme = isDarkMode.value) {
                MainScreen(preferenceManager, isDarkMode)
            }
        }
    }
}

@Composable
fun MainScreen(preferenceManager: PreferenceManager, isDarkMode: MutableState<Boolean>) {
    val navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route
    
    val showBottomBar = currentRoute != Screen.Splash.route

    Scaffold(
        bottomBar = { 
            if (showBottomBar) {
                BottomNavBar(navController) 
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(if (showBottomBar) innerPadding else PaddingValues(0.dp)),
            enterTransition = { fadeIn(animationSpec = tween(400)) + slideInHorizontally(initialOffsetX = { 300 }) },
            exitTransition = { fadeOut(animationSpec = tween(400)) + slideOutHorizontally(targetOffsetX = { -300 }) },
            popEnterTransition = { fadeIn(animationSpec = tween(400)) + slideInHorizontally(initialOffsetX = { -300 }) },
            popExitTransition = { fadeOut(animationSpec = tween(400)) + slideOutHorizontally(targetOffsetX = { 300 }) }
        ) {
            composable(Screen.Splash.route) {
                SplashScreen(navController)
            }
            composable(Screen.Home.route) {
                HomeScreen(navController, preferenceManager, isDarkMode)
            }
            composable(Screen.Menu.route) {
                MenuScreen(navController)
            }
            composable(
                route = Screen.DetailMenu.route,
                arguments = listOf(navArgument("menuId") { type = NavType.StringType })
            ) { backStackEntry ->
                val menuId = backStackEntry.arguments?.getString("menuId")
                DetailMenuScreen(navController, menuId)
            }
            composable(Screen.Profile.route) {
                ProfileScreen(navController, preferenceManager)
            }
            composable(Screen.EditProfile.route) {
                EditProfileScreen(navController, preferenceManager)
            }
            composable(Screen.Cart.route) {
                CartScreen(navController)
            }
        }
    }
}
