package com.example.todoapp.ui.edit

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.util.Log

/**
 * Created by Yasuhiro Suzuki on 2017/07/30.
 */
class EditTaskViewModel: ViewModel() {

    val title = ObservableField<String>()

    val content = ObservableField<String>()

    val TITLE = "title"

    val CONTENT = "content"

    fun save(onSuccess: () -> Unit, onError: (error: String) -> Unit) = when (validate()) {
        TITLE -> onError("No title!")
        CONTENT -> onError("No content!")
        else -> {
            Log.d("title", title.get())
            Log.d("content", content.get())
            onSuccess()
        }
    }

    fun validate() = if (!validateTitle()) TITLE else if (!validateContent()) CONTENT else ""

    fun validateTitle() = !title.get().isNullOrBlank()

    fun validateContent() = !content.get().isNullOrBlank()

    override fun onCleared() {
        super.onCleared()
    }

}