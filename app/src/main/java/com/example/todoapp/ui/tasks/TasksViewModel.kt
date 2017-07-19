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

    var lastItem: Pair<Int, TaskViewModel>? = null

    var observableListCallback: ObservableList.OnListChangedCallback<ObservableList<TaskViewModel>>? = null

    var onRemoveItem: () -> Unit = {}

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

    fun moveItem(from: Int, to: Int, onItemMoved: () -> Unit) {
        // Don't call OnListChangedCallback.
        removeObservableListCallback()
        moveItem(from, to)
        onItemMoved()
        restoreObservableListCallback()
    }

    private fun moveItem(from: Int, to: Int) {
        val target = taskItems[from]
        taskItems.removeAt(from)
        taskItems.add(to, target)
    }

    fun removeItem(from: Int) {
        storeLastItem(from)
        taskItems.removeAt(from)
        onRemoveItem()
    }

    fun storeLastItem(index: Int) {
        lastItem = Pair(index, taskItems[index])
    }

    fun restoreLastItems() {
        lastItem?.let { taskItems.add(it.first, it.second) }
    }

    fun addObservableListCallBack(callback: ObservableList.OnListChangedCallback<ObservableList<TaskViewModel>>) {
        taskItems.addOnListChangedCallback(callback)
        observableListCallback = callback
    }

    fun removeObservableListCallback() {
        observableListCallback?.let { taskItems.removeOnListChangedCallback(it) }
    }

    fun restoreObservableListCallback() {
        observableListCallback?.let { taskItems.addOnListChangedCallback(it) }
    }

    override fun onClickItem(task: Task) {
        Log.d("TaskNavigator", task.id.toString())
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
        removeObservableListCallback()
    }

}
