package com.example.todoapp.ui.edit

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.example.todoapp.data.TasksRepository
import com.example.todoapp.model.Task
import com.example.todoapp.util.DateUtil

/**
 * Created by Yasuhiro Suzuki on 2017/07/30.
 */

typealias OnSuccess = () -> Unit
typealias OnError = (error: String) -> Unit

class EditTaskViewModel: ViewModel() {

    val ERROR_TITLE = 1
    val ERROR_CONTENT = 2

    val title = ObservableField<String>()
    val content = ObservableField<String>()

    fun save(onSuccess: OnSuccess, onError: OnError) = when (validate()) {
        ERROR_TITLE -> onError("No title!")
        ERROR_CONTENT -> onError("No content!")
        else -> uploadTask(onSuccess, onError)
    }

    private fun uploadTask(onSuccess: OnSuccess, onError: OnError) {
        TasksRepository.saveTask(getTask()).subscribe({onSuccess()}, { onError(it.message?: "error") })
    }

    fun getTask() = Task(
            date = DateUtil.currentDate,
            title = title.get().trim(),
            description = content.get().trim()
    )

    fun validate() = if (!validateTitle()) ERROR_TITLE
    else if (!validateContent()) ERROR_CONTENT
    else 0

    fun validateTitle() = !title.get().isNullOrBlank()

    fun validateContent() = !content.get().isNullOrBlank()

    override fun onCleared() {
        super.onCleared()
    }

}