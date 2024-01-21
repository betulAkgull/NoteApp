package com.example.noteapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.noteapp.R

@Composable
fun HomeScreen(navController: NavController) {
    val notes = List(100) { "Note number ${it+1}" }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.TopStart,
    ) {
        Text(text = "Notes", fontSize = 32.sp, color = MaterialTheme.colorScheme.surface)
        if (notes.isNotEmpty()) {
            NotesList(notes = notes, navController)
        } else {
            EmptyList()
        }
        Fab(navController)
    }
}

@Composable
fun Fab(navController: NavController) {
    FloatingActionButton(
        onClick = {
            navController.navigate("Create Note")
        },
        modifier = Modifier
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

@Composable
fun NotesList(notes: List<String>, navController: NavController) {
    LazyColumn(modifier = Modifier.padding(top = 20.dp, start = 10.dp, end = 10.dp)) {
        items(notes) { note ->
            Note(note = note, navController)
        }
    }
}

@Composable
fun Note(note: String, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.surface)
            .clickable {
                navController.navigate("Detail")
            },
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()

        ) {
            Text(
                text = note,
                style = MaterialTheme.typography.bodyLarge,
                color = contentColorFor(MaterialTheme.colorScheme.surface),
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun EmptyList() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.TopStart,
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.Center),
            painter = painterResource(id = R.drawable.rafiki),
            contentDescription = "No Notes Image"
        )
        Text(
            modifier = Modifier
                .padding(top = 120.dp)
                .align(Alignment.Center),
            text = "Create your first note !",
            color = Color.White
        )
    }
}