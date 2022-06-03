package com.example.tourismonmarslogbook.ui.annotation

import androidx.lifecycle.ViewModel
import com.example.tourismonmarslogbook.database.NoteDao

class AnnotationViewModel(dataSource: NoteDao) : ViewModel() {

    val notes = dataSource.getNotes()

}