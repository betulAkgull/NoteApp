package com.example.noteapp.data.local.usecases

import com.example.noteapp.data.local.repository.NoteRepository
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(id: Long) = repository.deleteNote(id)

}