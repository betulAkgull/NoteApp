package com.example.noteapp.data.local.usecases

import com.example.noteapp.data.local.entity.Note
import com.example.noteapp.data.local.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavNotesUseCase @Inject constructor(
    private val repository: NoteRepository
) {

    operator fun invoke(): Flow<List<Note>> {
        return repository.getFavNotes()
    }

}