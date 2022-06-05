package com.fiap.tourismonmarslogbook.ui.annotation

import androidx.lifecycle.ViewModel
import com.fiap.tourismonmarslogbook.database.Note
import com.fiap.tourismonmarslogbook.database.NoteDao
import kotlinx.coroutines.*

class AnnotationViewModel(private val dataSource: NoteDao) : ViewModel() {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun notes() = dataSource.getNotes()

    fun startDeleteAnnotation(note: Note) {
        uiScope.launch {
            deleteNotes(note)
        }
    }

    private suspend fun deleteNotes(note: Note) {
        withContext(Dispatchers.IO) {
            dataSource.deleteItem(note)
        }
    }

}