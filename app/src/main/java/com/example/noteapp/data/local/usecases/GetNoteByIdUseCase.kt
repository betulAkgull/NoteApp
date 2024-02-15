package com.example.noteapp.data.local.usecases

import com.example.noteapp.data.local.entity.Note
import com.example.noteapp.data.local.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNoteByIdUseCase @Inject constructor(
    private val repository: NoteRepository
) {

    operator fun invoke(id: Long): Flow<Note> {
        return repository.getNoteById(id)
    }

}