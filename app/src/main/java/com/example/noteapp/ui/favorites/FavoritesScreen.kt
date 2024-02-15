package com.example.noteapp.ui.favorites

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.noteapp.common.Resource
import com.example.noteapp.data.local.entity.Note
import com.example.noteapp.ui.home.NoteItem

@Composable
fun FavoritesScreen(
    state: FavoritesState,
    modifier: Modifier,
    onFavoriteChange: (note: Note) -> Unit,
    onDeleteNote: (Long) -> Unit,
    onNoteClicked: (Long) -> Unit,
) {

    when (state.notes) {
        is Resource.Loading -> {
            CircularProgressIndicator()
        }

        is Resource.Success -> {
            val notes = state.notes.data
            LazyColumn(contentPadding = PaddingValues(4.dp), modifier = modifier) {
                itemsIndexed(notes) { index, item ->
                    NoteItem(
                        note = item,
                        onFavChange = onFavoriteChange,
                        onDeleteNote = onDeleteNote,
                        onNoteClick = onNoteClicked
                    )
                }
            }
        }

        is Resource.Error -> {
            Text(
                text = state.notes.message ?: "Unknown Error",
                color = MaterialTheme.colorScheme.error
            )
        }
    }

}
