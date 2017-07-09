package com.example.todoapp.ui.tasks

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import android.util.Log
import com.example.todoapp.data.TasksRepository
import com.example.todoapp.model.Task
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Yasuhiro Suzuki on 2017/06/18.
 */
class TasksViewModel: ViewModel(), TaskNavigator {

    val compositeDisposable = CompositeDisposable()

    val taskItems: ObservableList<TaskViewModel> = ObservableArrayList()

    val lastTaskItems: ObservableList<TaskViewModel> = ObservableArrayList()

    init {
        fetchTasks()
    }

    fun fetchTasks() {
        Observable.fromIterable(TasksRepository.getTasks())
                .map { TaskViewModel(it) }
                .doOnNext { it.setNavigator(this) }
                .doOnNext { taskItems.add(it) }
                .subscribe()
    }

    fun moveItem(from: Int, to: Int) {
        val target = taskItems[from]
        taskItems.removeAt(from)
        taskItems.add(to, target)
    }

    fun removeItem(index: Int) {
        storeLastItems()
        taskItems.removeAt(index)
    }

    fun storeLastItems() {
        lastTaskItems.clear()
        lastTaskItems.addAll(taskItems)
    }

    override fun onClickItem(task: Task) {
        Log.d("TaskNavigator", task.id.toString())
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}