package com.nhom2.noteapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.nhom2.noteapp.Model.Notes
import java.util.concurrent.locks.Lock

@Database(entities = [Notes::class], version = 1)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun getNoteDao():MainDAO
    companion object{
        @Volatile
        private var instance:NoteDatabase? =null
        private val Lock = Any()

    }
}