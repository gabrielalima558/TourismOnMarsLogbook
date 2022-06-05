package com.fiap.tourismonmarslogbook.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "team_note")
class Note (
    @PrimaryKey(autoGenerate = true)
    var noteId: Long = 0L,

    @ColumnInfo(name = "team_title")
    var title: String = "",

    @ColumnInfo(name = "team_description")
    var description: String = ""
    )