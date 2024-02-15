package com.example.noteapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.noteapp.R
import com.example.noteapp.common.Resource
import com.example.noteapp.data.local.entity.Note

@Composable
fun HomeScreen(
    state: HomeState,
    onFavChange: (note: Note) -> Unit,
    onDeleteNote: (id: Long) -> Unit,
    onNoteClick: (Long) -> Unit
) {

    when (state.notes) {
        is Resource.Loading -> {
            CircularProgressIndicator()
        }

        is Resource.Success -> {

            val notes = state.notes.data
            HomeDetail(
                notes = notes,
                onFavChange = onFavChange,
                onDeleteNote = onDeleteNote,
                onNoteClick = onNoteClick
            )
        }

        is Resource.Error -> {
            EmptyNotes()
        }
    }

}

@Composable
fun HomeDetail(
    notes: List<Note>,
    onFavChange: (note: Note) -> Unit,
    onDeleteNote: (id: Long) -> Unit,
    onNoteClick: (Long) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.TopStart,
    ) {
        Text(text = "Notes", fontSize = 32.sp, color = MaterialTheme.colorScheme.surface)
        LazyColumn(modifier = Modifier.padding(top = 20.dp, start = 10.dp, end = 10.dp)) {
            itemsIndexed(notes) { index, item ->
                NoteItem(
                    note = item,
                    onFavChange = onFavChange,
                    onDeleteNote = onDeleteNote,
                    onNoteClick = onNoteClick
                )
            }
        }
        //Fab(navController)
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteItem(
    note: Note,
    onFavChange: (note: Note) -> Unit,
    onDeleteNote: (id: Long) -> Unit,
    onNoteClick: (Long) -> Unit
) {
    val icon = if (note.isFav) Icons.Default.Favorite else Icons.Default.FavoriteBorder
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.surface),
        onClick = {
            onNoteClick(note.id)
        }
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            IconButton(onClick = { onFavChange(note) }) {
                Icon(imageVector = icon, contentDescription = null)
            }
            IconButton(onClick = { onDeleteNote(note.id) }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
        }


        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()

        ) {
            Text(
                text = note.title,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.size(6.dp))

            Text(
                text = note.description,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium
            )

        }
    }
}

@Composable
fun EmptyNotes() {
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
