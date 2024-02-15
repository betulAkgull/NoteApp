package com.example.noteapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.noteapp.ui.createNote.CreateNoteScreen
import com.example.noteapp.ui.detail.DetailAssistedFactory
import com.example.noteapp.ui.detail.DetailScreen
import com.example.noteapp.ui.favorites.FavoritesScreen
import com.example.noteapp.ui.favorites.FavoritesViewModel
import com.example.noteapp.ui.home.HomeScreen
import com.example.noteapp.ui.home.HomeViewModel

@Composable
fun NoteAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    favoritesViewModel: FavoritesViewModel,
    assistedFactory: DetailAssistedFactory
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Destinations.Home.title
    ) {
        composable(Destinations.Home.title) {
            val state by homeViewModel.state.collectAsState()
            HomeScreen(
                state = state,
                onFavChange = homeViewModel::onFavNoteChanged,
                onDeleteNote = homeViewModel::onDeleteNote,
                onNoteClick = {
                    navController.navigateToSingleTop(
                        route = "${Destinations.Detail.title}?id=$it"
                    )
                },
            )
        }
        composable(route = Destinations.Favorites.title) {
            val state by favoritesViewModel.state.collectAsState()
            FavoritesScreen(
                state = state,
                modifier = modifier,
                onFavoriteChange = favoritesViewModel::onFavoriteChange,
                onDeleteNote = favoritesViewModel::onDeleteNote,
                onNoteClicked = {
                    navController.navigateToSingleTop(
                        route = "${Destinations.Detail.title}?id = $it"
                    )
                }
            )
        }
        composable("Create Note") {
            CreateNoteScreen()
        }
        composable(
            route = "${Destinations.Detail.title}?id={id}",
            arguments = listOf(
                navArgument("id") {
                    NavType.LongType
                    defaultValue = -1L
                }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getLong("id") ?: -1L
            DetailScreen(
                id = id, assistedFactory = assistedFactory,
                navigateUp = { navController.navigateUp() }
            )
        }
    }
}

fun NavHostController.navigateToSingleTop(route: String) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}