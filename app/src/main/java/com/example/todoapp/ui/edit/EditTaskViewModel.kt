package com.example.todoapp.ui.edit

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.example.todoapp.data.TasksRepository
import com.example.todoapp.model.Task
import com.example.todoapp.util.DateUtil
import com.example.todoapp.util.Error
import com.example.todoapp.util.OK
import com.example.todoapp.util.ValidateResult
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Yasuhiro Suzuki on 2017/07/30.
 */

typealias OnSuccess = () -> Unit
typealias OnError = (error: String?) -> Unit

class EditTaskViewModel(val tasksRepository: TasksRepository) : ViewModel() {

    val compositeDisposable = CompositeDisposable()

    val taskId = ObservableField<String>()

    val title = ObservableField<String>()

    val content = ObservableField<String>()

    val task: Task
        get() = Task(
                date = DateUtil.currentDate,
                title = title.get().trim(),
                description = content.get().trim())

    fun fetchTask(taskId: String) {
        val disposable = tasksRepository
                .getTask(taskId)
                .subscribe(
                        { setTask(it) },
                        { it.printStackTrace() }
                )
        compositeDisposable.add(disposable)
    }

    private fun setTask(task: Task) {
        taskId.set(task.id)
        title.set(task.title)
        content.set(task.description)
    }

    fun save(onSuccess: OnSuccess, onError: OnError) {
        val result = validate(title.get(), content.get())
        when (result) {
            is Error -> onError(result.throwable.message)
            is OK -> addOrUpdate(onSuccess, onError)
        }
    }

    private fun addOrUpdate(onSuccess: OnSuccess, onError: OnError) = when {
        taskId.get().isNullOrBlank() -> addTask(onSuccess, onError)
        else -> updateTask(onSuccess, onError)
    }

    private fun addTask(onSuccess: OnSuccess, onError: OnError) {
        val disposable = tasksRepository
                .createTask(task)
                .subscribe(
                        { onSuccess() },
                        { onError(it.message) }
                )
        compositeDisposable.add(disposable)
    }

    private fun updateTask(onSuccess: OnSuccess, onError: OnError) {
        val disposable = tasksRepository
                .updateTask(task.copy(id = taskId.get()))
                .subscribe(
                        { onSuccess() },
                        { onError(it.message) }
                )
        compositeDisposable.add(disposable)
    }

    fun validate(title: String?, content: String?): ValidateResult = when {
        title.isNullOrBlank() -> Error(Throwable("No title!"))
        content.isNullOrBlank() -> Error(Throwable("No content!"))
        else -> OK
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}