package com.example.noteapp.data.local.usecases

import com.example.noteapp.data.local.entity.Note
import com.example.noteapp.data.local.repository.NoteRepository
import javax.inject.Inject

class UpdateNoteUseCase @Inject constructor(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(note: Note) = repository.updateNote(note)

}