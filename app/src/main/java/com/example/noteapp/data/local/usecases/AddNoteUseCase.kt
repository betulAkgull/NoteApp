package com.example.noteapp.data.local.usecases

import com.example.noteapp.data.local.entity.Note
import com.example.noteapp.data.local.repository.NoteRepository
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(
    private val repository: NoteRepository
) {

    //operator makes it easy to reach as a function
    suspend operator fun invoke(note: Note) = repository.addNote(note)

}