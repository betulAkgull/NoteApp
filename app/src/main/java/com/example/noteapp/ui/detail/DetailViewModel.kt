package com.example.noteapp.ui.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.noteapp.data.local.entity.Note
import com.example.noteapp.data.local.usecases.AddNoteUseCase
import com.example.noteapp.data.local.usecases.DeleteNoteUseCase
import com.example.noteapp.data.local.usecases.GetNoteByIdUseCase
import com.example.noteapp.data.local.usecases.UpdateNoteUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Date


class DetailViewModel @AssistedInject constructor(
    private val addNoteUseCase: AddNoteUseCase,
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    @Assisted private val id: Long
) : ViewModel() {
    var state by mutableStateOf(DetailState())
        private set
    val isFormNotBlank: Boolean
        get() = state.title.isNotEmpty() && state.description.isNotEmpty()
    private val note: Note
        get() = state.run {
            Note(
                id,
                title,
                description,
                dateCreated,
                isDone,
                isFav
            )
        }

    init {
        initialize()
    }

    private fun initialize() {
        val isUpdatingNote = id != -1L
        state = state.copy(isUpdatingNote = isUpdatingNote)
        if (isUpdatingNote) {
            getNoteById()
        }
    }

    private fun getNoteById() {
        viewModelScope.launch {
            getNoteByIdUseCase(id).collectLatest {
                state = state.copy(
                    id = it.id,
                    title = it.title,
                    description = it.description,
                    dateCreated = it.dateAdded,
                    isDone = it.isDone,
                    isFav = it.isFav
                )
            }
        }
    }

    fun onTitleChanged(title: String) {
        state = state.copy(title = title)
    }

    fun onDescriptionChanged(description: String) {
        state = state.copy(description = description)
    }

    fun onFavChanged(favorite: Boolean) {
        state = state.copy(isFav = favorite)
    }

    fun addOrUpdateNote() = viewModelScope.launch {
        addNoteUseCase(note = note)
    }

}

class DetailViewModelFactory(
    private val id: Long,
    private val assistedFactory: DetailAssistedFactory
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return assistedFactory.create(id) as T
    }
}


@AssistedFactory
interface DetailAssistedFactory {
    fun create(id: Long): DetailViewModel
}

data class DetailState(
    val id: Long = 0,
    val title: String = "",
    val description: String = "",
    val isFav: Boolean = false,
    val dateCreated: Date = Date(),
    val isDone: Boolean = false,
    val isUpdatingNote: Boolean = false
)