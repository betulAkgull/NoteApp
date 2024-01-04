package com.example.noteapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destinations(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : Destinations("home", "Home", Icons.Rounded.Home)
    object Favorites : Destinations("favorites", "Favorites", Icons.Rounded.Favorite)
}