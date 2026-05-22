package com.irene.mycafe.ui

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Menu : Screen("menu")
    object DetailMenu : Screen("detail_menu/{menuId}") {
        fun createRoute(menuId: String) = "detail_menu/$menuId"
    }
    object Profile : Screen("profile")
    object EditProfile : Screen("edit_profile")
    object Cart : Screen("cart")
    object Splash : Screen("splash")
}
