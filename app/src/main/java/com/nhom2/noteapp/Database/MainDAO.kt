package com.nhom2.noteapp.Database

import android.icu.text.CaseMap.Title
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nhom2.noteapp.Model.Notes

@Dao
interface MainDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(notes: Notes)


    @Query("SELECT * FROM Notes ORDER BY id DESC")
    fun getAllNotes():LiveData<List<Notes>>

    @Query("UPDATE Notes SET title = :title, notes = :notes WHERE id ")
    fun updateNote( id:Int, title: String, notes: String )

    @Delete
    suspend fun deleteNote(notes: Notes)
}