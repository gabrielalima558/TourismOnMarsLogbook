package com.fiap.tourismonmarslogbook.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    @Insert
    fun insertNote(note: Note)

    @Transaction
    @Query("SELECT * FROM team_note")
    fun getNotes(): LiveData<List<Note>>

    @Delete
    fun deleteItem(note: Note)

}