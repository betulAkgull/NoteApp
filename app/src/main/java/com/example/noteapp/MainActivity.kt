package com.example.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.noteapp.ui.detail.DetailAssistedFactory
import com.example.noteapp.ui.favorites.FavoritesViewModel
import com.example.noteapp.ui.home.HomeViewModel
import com.example.noteapp.ui.navigation.Destinations
import com.example.noteapp.ui.navigation.NoteAppBottomBar
import com.example.noteapp.ui.navigation.NoteAppNavHost
import com.example.noteapp.ui.navigation.navigateToSingleTop
import com.example.noteapp.ui.theme.NoteAppTheme
import com.example.noteapp.ui.topBar.NoteAppTopBar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var assistedFactory: DetailAssistedFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onSurface
                ) {
                    NoteApp()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun NoteApp() {
        val homeViewModel: HomeViewModel = viewModel()
        val favoritesViewModel: FavoritesViewModel = viewModel()
        val navController = rememberNavController()
        val currentTab by remember {
            mutableStateOf(
                TabScreen.Home
            )
        }
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentScreen = backStackEntry?.destination?.route
        Scaffold(
            topBar = {
                if (currentScreen != null) {
                    NoteAppTopBar(
                        currentScreen = currentScreen,
                        canNavigateBack = navController.previousBackStackEntry != null,
                        navController
                    )
                }
            },
            bottomBar = {
                NoteAppBottomBar(navController = navController)
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        navController.navigateToSingleTop(Destinations.Detail.title)
                    }, modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.BottomEnd),
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.background,
                    shape = CircleShape
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = "Fab Button",
                    )
                }
            }
        ) {
            NoteAppNavHost(
                modifier = Modifier.padding(it),
                navController = navController,
                homeViewModel = homeViewModel,
                favoritesViewModel = favoritesViewModel,
                assistedFactory = assistedFactory
            )
        }
    }

    enum class TabScreen {
        Home, Favorites,
    }
}
