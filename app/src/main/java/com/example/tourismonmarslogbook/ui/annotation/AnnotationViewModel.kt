package com.example.tourismonmarslogbook.ui.annotation

import androidx.lifecycle.ViewModel
import com.example.tourismonmarslogbook.database.Note
import com.example.tourismonmarslogbook.database.NoteDao

class AnnotationViewModel(private val dataSource: NoteDao) : ViewModel() {

    val notes = dataSource.getNotes()

    fun deleteNotes(note: Note) {
        dataSource.deleteItem(note)
    }

}