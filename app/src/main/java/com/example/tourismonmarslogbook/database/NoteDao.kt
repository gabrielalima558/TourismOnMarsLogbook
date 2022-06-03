package com.example.tourismonmarslogbook.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface NoteDao {
    @Insert
    fun insertNote(note: Note)

    @Transaction
    @Query("SELECT * FROM team_note")
    fun getNotes(): LiveData<List<Note>>

}