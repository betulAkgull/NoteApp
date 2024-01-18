package com.example.noteapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.noteapp.ui.createNote.CreateNoteScreen
import com.example.noteapp.ui.detail.DetailScreen
import com.example.noteapp.ui.favorites.FavoritesScreen
import com.example.noteapp.ui.home.HomeScreen

@Composable
fun NoteAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = "Home"
    ) {
        composable("Home") {
            HomeScreen(navController)
        }
        composable("Favorites") {
            FavoritesScreen()
        }
        composable("Create Note") {
            CreateNoteScreen()
        }
        composable("Detail") {
            DetailScreen()
        }
    }
}