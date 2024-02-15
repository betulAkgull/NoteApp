package com.example.noteapp.ui.detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    id: Long,
    assistedFactory: DetailAssistedFactory,
    navigateUp: () -> Unit
) {
    val viewModel = viewModel(
        modelClass = DetailViewModel::class.java,
        factory = DetailViewModelFactory(
            id = id,
            assistedFactory = assistedFactory
        )
    )

    val state = viewModel.state
    DetailScreen(
        modifier = modifier,
        isUpdatingNote = state.isUpdatingNote,
        title = state.title,
        description = state.description,
        isFav = state.isFav,
        isFormNotBlank = viewModel.isFormNotBlank,
        onFavChanged = viewModel::onFavChanged,
        onTitleChanged = viewModel::onTitleChanged,
        onDescChanged = viewModel::onDescriptionChanged,
        onButtonClick = {
            viewModel.addOrUpdateNote()
            navigateUp
        },
        onNavigate = navigateUp
    )

}

@Composable
private fun DetailScreen(
    modifier: Modifier,
    isUpdatingNote: Boolean,
    title: String,
    description: String,
    isFav: Boolean,
    isFormNotBlank: Boolean,
    onFavChanged: (Boolean) -> Unit,
    onTitleChanged: (String) -> Unit,
    onDescChanged: (String) -> Unit,
    onButtonClick: () -> Unit,
    onNavigate: () -> Unit
) {

    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        TopSection(
            title = title,
            isFav = isFav,
            onFavChanged = onFavChanged,
            onTitleChanged = onTitleChanged,
            onNavigate = onNavigate
        )
        Spacer(modifier = Modifier.size(12.dp))
        AnimatedVisibility(isFormNotBlank) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = onButtonClick) {
                    val icon = if (isUpdatingNote) Icons.Default.Refresh else Icons.Default.Check
                    Icon(imageVector = icon, contentDescription = null)
                }
            }
        }
        Spacer(modifier = Modifier.size(8.dp))
        NotesTextField(
            modifier = Modifier.weight(1f),
            value = description,
            onValueChanged = onDescChanged,
            label = "Description",
        )
    }
}

@Composable
fun TopSection(
    modifier: Modifier = Modifier,
    title: String,
    isFav: Boolean,
    onFavChanged: (Boolean) -> Unit,
    onTitleChanged: (String) -> Unit,
    onNavigate: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NotesTextField(
            modifier = Modifier.weight(1f),
            value = title,
            onValueChanged = onTitleChanged,
            label = "Title",
            labelAlignment = TextAlign.Center
        )
        IconButton(onClick = { onFavChanged(!isFav) }) {
            val icon = if (isFav) Icons.Default.Favorite else Icons.Default.FavoriteBorder
            Icon(imageVector = icon, contentDescription = "favButton")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NotesTextField(
    modifier: Modifier,
    value: String,
    onValueChanged: (String) -> Unit,
    label: String,
    labelAlignment: TextAlign? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        modifier = modifier,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            textColor = MaterialTheme.colorScheme.onPrimary,
        ),
        placeholder = {
            Text(
                text = "Insert $label",
                textAlign = labelAlignment,
                modifier = modifier.fillMaxWidth()
            )
        }
    )
}