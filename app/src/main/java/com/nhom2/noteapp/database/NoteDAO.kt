package com.nhom2.noteapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nhom2.noteapp.model.Note

@Dao
interface NoteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note:Note)

    @Update
    suspend fun updateNote(note:Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM NOTES ORDER BY id DESC")
    fun getAllNote():LiveData<List<Note>>

    @Query("SELECT * FROM NOTES WHERE noteTitle LIKE:query OR noteDesc LIKE:query ")
    fun searchNote(query: String?):LiveData<List<Note>>

    @Query("SELECT * FROM NOTES WHERE priority=3")
    fun getHighNotes():LiveData<List<Note>>

    @Query("SELECT * FROM NOTES WHERE priority=2")
    fun getMediumNotes():LiveData<List<Note>>

    @Query("SELECT * FROM NOTES WHERE priority=1")
    fun getLowNotes():LiveData<List<Note>>
}
