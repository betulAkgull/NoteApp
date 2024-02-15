package com.example.noteapp.data.local.repository

import com.example.noteapp.data.local.entity.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getNotes(): Flow<List<Note>>

    fun getNoteById(id: Long): Flow<Note>

    suspend fun addNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(id: Long)

    fun getFavNotes(): Flow<List<Note>>

    suspend fun deleteAllNotes()
}