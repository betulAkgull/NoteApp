package com.example.noteapp.data.local.repository

import com.example.noteapp.data.local.dao.NoteDao
import com.example.noteapp.data.local.entity.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository {
    override fun getNotes(): Flow<List<Note>> {
        return noteDao.getNotes()
    }

    override fun getNoteById(id: Long): Flow<Note> {
        return noteDao.getNoteById(id)
    }

    override suspend fun addNote(note: Note) {
        return noteDao.addNote(note)
    }

    override suspend fun updateNote(note: Note) {
        return noteDao.updateNote(note)
    }

    override suspend fun deleteNote(id: Long) {
        return noteDao.deleteNote(id)
    }

    override fun getFavNotes(): Flow<List<Note>> {
        return noteDao.getFavNotes()
    }

    override suspend fun deleteAllNotes() {
        return noteDao.deleteAllNotes()
    }
}