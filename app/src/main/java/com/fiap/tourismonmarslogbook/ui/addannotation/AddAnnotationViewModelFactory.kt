package com.fiap.tourismonmarslogbook.ui.addannotation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fiap.tourismonmarslogbook.database.NoteDao

class AddAnnotationViewModelFactory(
    private val dataSource: NoteDao
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddAnnotationViewModel::class.java)) {
            return AddAnnotationViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}