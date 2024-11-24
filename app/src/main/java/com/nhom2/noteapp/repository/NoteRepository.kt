package com.nhom2.noteapp.repository

import androidx.room.Query
import com.nhom2.noteapp.database.NoteDatabase
import com.nhom2.noteapp.model.Note

class NoteRepository(private val db:NoteDatabase) {
    suspend fun insertNote(note:Note) = db.getNoteDao().insertNote(note)
    suspend fun updateNote(note:Note) = db.getNoteDao().updateNote(note)
    suspend fun deleteNote(note:Note) = db.getNoteDao().deleteNote(note)

    fun getAllNotes() = db.getNoteDao().getAllNote()
    fun searchNote(query: String?) =db.getNoteDao().searchNote(query)
}