package com.example.todoapp.ui.tasks

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.todoapp.data.TasksRepository
import com.example.todoapp.ui.edit.EditTaskViewModel
import javax.inject.Inject

/**
 * Created by Yasuhiro Suzuki on 2017/08/19.
 */
class EditTaskViewModelFactory @Inject constructor(val repository: TasksRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditTaskViewModel::class.java)) {
            return EditTaskViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}