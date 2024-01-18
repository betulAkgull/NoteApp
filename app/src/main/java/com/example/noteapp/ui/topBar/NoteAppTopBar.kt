package com.example.noteapp.ui.topBar

import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteAppTopBar(currentScreen: String, canNavigateBack: Boolean, navController: NavController) {
    val context = LocalContext.current
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(text = currentScreen, textAlign = TextAlign.Center, fontSize = 18.sp)
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton({
                    navController.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "back button"
                    )
                }
            }
        },
        actions = {
            if (currentScreen == "Detail") {
                IconButton(onClick = {
                    Toast.makeText(
                        context,
                        "Edit Button Clicked",
                        Toast.LENGTH_SHORT
                    ).show()
                }) {
                    Icon(imageVector = Icons.Filled.Edit, contentDescription = "edit button")
                }
            }
            if (currentScreen == "Favorites") {
                IconButton(onClick = {
                    Toast.makeText(
                        context,
                        "Delete Button Clicked",
                        Toast.LENGTH_SHORT
                    ).show()
                }) {
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = "delete button")
                }
            }
            if (currentScreen == "Create Note") {
                IconButton(onClick = {
                    Toast.makeText(
                        context,
                        "Save Button Clicked",
                        Toast.LENGTH_SHORT
                    ).show()
                }) {
                    Icon(imageVector = Icons.Filled.Done, contentDescription = "save button")
                }
                IconButton(onClick = {
                    Toast.makeText(
                        context,
                        "Delete Button Clicked",
                        Toast.LENGTH_SHORT
                    ).show()
                }) {
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = "delete button")
                }
            }
        }
    )
}