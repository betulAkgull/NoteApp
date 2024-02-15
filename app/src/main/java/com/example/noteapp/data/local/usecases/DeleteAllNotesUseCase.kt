package com.example.noteapp.data.local.usecases

import com.example.noteapp.data.local.repository.NoteRepository
import javax.inject.Inject

class DeleteAllNotesUseCase @Inject constructor(
    private val repository: NoteRepository
) {

    suspend operator fun invoke() = repository.deleteAllNotes()

}