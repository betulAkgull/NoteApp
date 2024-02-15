package com.example.noteapp.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.common.Resource
import com.example.noteapp.data.local.entity.Note
import com.example.noteapp.data.local.usecases.DeleteNoteUseCase
import com.example.noteapp.data.local.usecases.GetFavNotesUseCase
import com.example.noteapp.data.local.usecases.UpdateNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val getFavNotesUseCase: GetFavNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {
    private val _state: MutableStateFlow<FavoritesState> = MutableStateFlow(FavoritesState())
    val state: StateFlow<FavoritesState> = _state.asStateFlow()


    init {
        getFavoriteNotes()
    }

    private fun getFavoriteNotes() {
        getFavNotesUseCase().onEach {
            _state.value = FavoritesState(notes = Resource.Success(it))
        }

            .catch {
                _state.value = FavoritesState(Resource.Error(it.message))
            }
            .launchIn(viewModelScope)
    }

    fun onFavoriteChange(note: Note) {
        viewModelScope.launch {
            updateNoteUseCase(note.copy(isFav = !note.isFav))
        }
    }

    fun onDeleteNote(id: Long) {
        viewModelScope.launch {
            deleteNoteUseCase(id)
        }
    }

}

data class FavoritesState(
    val notes: Resource<List<Note>> = Resource.Loading
)