package com.example.todoapp.ui.edit

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.util.Log
import com.example.todoapp.model.Task
import com.example.todoapp.util.DateUtil

/**
 * Created by Yasuhiro Suzuki on 2017/07/30.
 */

typealias OnSuccess = (task: Task) -> Unit
typealias OnError = (error: String) -> Unit

class EditTaskViewModel: ViewModel() {

    val TITLE = "title"

    val CONTENT = "content"

    val title = ObservableField<String>()

    val content = ObservableField<String>()

    fun save(onSuccess: OnSuccess, onError: OnError) = when (validate()) {
        TITLE -> onError("No title!")
        CONTENT -> onError("No content!")
        else -> uploadTask(onSuccess, onError)
    }

    private fun uploadTask(onSuccess: OnSuccess, onError: OnError) {
        Log.d("title", title.get())
        Log.d("content", content.get())
        onSuccess(getTask())
    }

    fun getTask() = Task(DateUtil.timestump, DateUtil.currentDate, title.get(), content.get())

    fun validate() = if (!validateTitle()) TITLE else if (!validateContent()) CONTENT else ""

    fun validateTitle() = !title.get().isNullOrBlank()

    fun validateContent() = !content.get().isNullOrBlank()

    override fun onCleared() {
        super.onCleared()
    }

}