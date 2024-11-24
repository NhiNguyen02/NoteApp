package com.nhom2.noteapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nhom2.noteapp.model.Note
import com.nhom2.noteapp.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(app: Application, private val noteRepository: NoteRepository): AndroidViewModel(app) {
    fun addNote (note:Note) =
        viewModelScope.launch {
            noteRepository.insertNote(note)
        }
    fun updateNote(note:Note) =
        viewModelScope.launch {
            noteRepository.updateNote(note)
        }
    fun deleteNote(note: Note) =
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }
    fun getAllNote() = noteRepository.getAllNotes()
    fun searchNote(query:String?) = noteRepository.searchNote(query)
}