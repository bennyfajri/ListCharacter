package com.drsync.listcharacter.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorited : Screen("favorited")
    object Profile : Screen("profile")
    object DetailCharacter : Screen("home/{charId}") {
        fun createRoute(charId: Int) = "home/$charId"
    }
}