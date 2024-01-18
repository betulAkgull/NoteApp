package com.example.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.noteapp.ui.navigation.NoteAppBottomBar
import com.example.noteapp.ui.navigation.NoteAppNavHost
import com.example.noteapp.ui.theme.NoteAppTheme
import com.example.noteapp.ui.topBar.NoteAppTopBar

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
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
                        }
                    ) {
                        NoteAppNavHost(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(it),
                            navController = navController
                        )
                    }

                }
            }
        }
    }
}
