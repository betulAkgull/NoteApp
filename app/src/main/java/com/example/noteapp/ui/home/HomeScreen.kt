package com.example.noteapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noteapp.R

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.TopStart,
    ) {
        Text(text = "Notes", fontSize = 32.sp, color = MaterialTheme.colorScheme.surface)
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
        Fab()
    }
}

@Composable
fun Fab() {
    FloatingActionButton(
        onClick = { /* Handle FAB click here */ },
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