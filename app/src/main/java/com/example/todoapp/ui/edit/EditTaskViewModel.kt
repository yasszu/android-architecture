package com.example.todoapp.ui.edit

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.example.todoapp.MyApplication
import com.example.todoapp.data.TasksRepository
import com.example.todoapp.model.Task
import com.example.todoapp.util.DateUtil
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by Yasuhiro Suzuki on 2017/07/30.
 */

typealias OnSuccess = () -> Unit
typealias OnError = (error: String) -> Unit

class EditTaskViewModel: ViewModel() {

    @Inject
    lateinit var tasksRepository: TasksRepository

    val ERROR_TITLE = 1

    val ERROR_CONTENT = 2

    val compositeDisposable = CompositeDisposable()

    val title = ObservableField<String>()

    val content = ObservableField<String>()

    val task: Task
        get() = Task(
            date = DateUtil.currentDate,
            title = title.get().trim(),
            description = content.get().trim()
        )

    init {
        MyApplication.appDatabaseComponent.inject(this)
    }

    fun save(onSuccess: OnSuccess, onError: OnError) = when (validate()) {
        ERROR_TITLE -> onError("No title!")
        ERROR_CONTENT -> onError("No content!")
        else -> saveTask(onSuccess, onError)
    }

    private fun saveTask(onSuccess: OnSuccess, onError: OnError) {
        val disposable = tasksRepository
                .saveTask(task)
                .subscribe(
                        { onSuccess() },
                        { onError(it.message?: "error") }
                )
        compositeDisposable.add(disposable)
    }

    fun validate(): Int {
        return if (title.get().isNullOrBlank()) ERROR_TITLE
        else if (content.get().isNullOrBlank()) ERROR_CONTENT
        else 0
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}