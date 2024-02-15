package com.example.noteapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.noteapp.data.local.entity.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(note: Note)

    @Query("DELETE FROM notes WHERE id = :id ")
    suspend fun deleteNote(id: Long)

    @Query("SELECT * FROM notes WHERE isFav = 1 ORDER BY dateAdded DESC")
    fun getFavNotes(): Flow<List<Note>>

    @Query("DELETE FROM notes")
    suspend fun deleteAllNotes()

    @Query("SELECT * FROM notes WHERE id = :id ORDER BY dateAdded DESC")
    fun getNoteById(id: Long): Flow<Note>

    @Query("SELECT * FROM notes ORDER BY dateAdded DESC")
    fun getNotes(): Flow<List<Note>>

}