package com.nhom2.noteapp.Database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nhom2.noteapp.Model.Notes

@Database(entities = [Notes::class], version = 2, exportSchema = false)
abstract class RoomDb : RoomDatabase() {

    // Singleton instance
    companion object {
        @Volatile
        private var database: RoomDb? = null
        private const val DATABASE_NAME = "NoteApp"

        // Synchronized function to get the database instance
        fun getInstance(context: Context): RoomDb {
            return database ?: synchronized(this) {
                database ?: Room.databaseBuilder(
                    context.applicationContext,
                    RoomDb::class.java,
                    DATABASE_NAME
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build().also { database = it }
            }
        }
    }

    abstract fun mainDAO(): MainDAO
}
