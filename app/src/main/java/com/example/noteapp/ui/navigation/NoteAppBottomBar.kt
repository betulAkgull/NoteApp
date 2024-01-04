package com.example.noteapp.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavHostController

@Composable
fun NoteAppBottomBar(
    navController: NavHostController
) {
    val list = listOf(
        Destinations.Home,
        Destinations.Favorites
    )
    val selectedIndex = rememberSaveable {
        mutableIntStateOf(0)
    }

    NavigationBar {
        list.forEachIndexed { index, destination ->
            NavigationBarItem(
                label = { Text(text = destination.title) },
                selected = index == selectedIndex.value,
                onClick = {
                    selectedIndex.value = index
                    navController.navigate(list[index].route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = destination.title
                    )
                })
        }
    }
}