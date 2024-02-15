package com.example.noteapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destinations(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : Destinations("Home", "Home", Icons.Rounded.Home)
    object Favorites : Destinations("Favorites", "Favorites", Icons.Rounded.Favorite)
    object Detail : Destinations("Detail", "Detail", Icons.Default.Info)
}