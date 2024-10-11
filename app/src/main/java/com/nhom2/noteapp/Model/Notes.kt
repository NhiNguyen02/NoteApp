package com.nhom2.noteapp.Model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Notes")

data class Notes(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val title:String,
    val notes:String,
    val date:String,
    val pinned:Boolean
)
