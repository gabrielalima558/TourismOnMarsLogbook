package com.example.tourismonmarslogbook.ui.annotation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tourismonmarslogbook.database.NoteDao

class AnnotationViewModelFactory(
    private val dataSource: NoteDao
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AnnotationViewModel::class.java)) {
            return AnnotationViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}