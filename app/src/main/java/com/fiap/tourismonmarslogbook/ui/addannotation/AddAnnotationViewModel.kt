package com.fiap.tourismonmarslogbook.ui.addannotation

import androidx.lifecycle.ViewModel
import com.fiap.tourismonmarslogbook.database.Note
import com.fiap.tourismonmarslogbook.database.NoteDao
import kotlinx.coroutines.*

class AddAnnotationViewModel(val datasource: NoteDao): ViewModel() {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun startInsertAnnotation(note: Note) {
        uiScope.launch {
            insertAnnotation(note)
        }
    }

    private suspend fun insertAnnotation(note: Note) {
        withContext(Dispatchers.IO) {
            datasource.insertNote(note)
        }
    }
}