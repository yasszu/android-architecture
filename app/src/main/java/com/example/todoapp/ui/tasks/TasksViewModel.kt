package com.example.todoapp.ui.tasks

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import android.databinding.ObservableList.OnListChangedCallback
import android.view.View
import com.example.todoapp.data.TasksRepository
import com.example.todoapp.model.Task
import com.example.todoapp.ui.edit.OnSuccess
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Yasuhiro Suzuki on 2017/06/18.
 */
class TasksViewModel(val tasksRepository: TasksRepository) : ViewModel(), TaskNavigator {

    interface Listener {
        fun onRemoveItem()
        fun onClickFAB()
        fun onClickItem(task: Task)
    }

    val compositeDisposable = CompositeDisposable()

    val taskItems: ObservableList<TaskViewModel> = ObservableArrayList<TaskViewModel>()

    val size: Int
        get() = taskItems.size

    var lastItem: Pair<Int, TaskViewModel>? = null

    var observableListCallback: OnListChangedCallback<ObservableList<TaskViewModel>>? = null

    var listener: Listener? = null

    fun fetchTasks() {
        val disposable = tasksRepository
                .getTasks()
                .subscribe(
                        { setTasks(it) },
                        { it.printStackTrace() })
        compositeDisposable.add(disposable)
    }

    fun setTasks(tasks: List<Task>) {
        Flowable.fromIterable(tasks)
                .map { TaskViewModel(it) }
                .doOnNext { it.setNavigator(this) }
                .doOnNext { taskItems.add(it) }
                .subscribe()
    }

    fun deleteTasks() {
        val disposable = tasksRepository
                .deleteAllTasks()
                .subscribe(
                        { removeAllItems() },
                        { it.printStackTrace() })
        compositeDisposable.add(disposable)
    }

    fun deleteTask(id: String, onSuccess: OnSuccess) {
        val disposable = tasksRepository
                .deleteTask(id)
                .subscribe(
                        { onSuccess() },
                        { it.printStackTrace() }
                )
        compositeDisposable.add(disposable)
    }

    private fun saveTask(task: Task, onSuccess: OnSuccess) {
        val disposable = tasksRepository
                .createTask(task)
                .subscribe(
                        { onSuccess() },
                        { it.printStackTrace() }
                )
        compositeDisposable.add(disposable)
    }

    fun removeItem(from: Int) {
        val taskId = taskItems[from].task.get().id
        storeLastItem(from)
        deleteTask(taskId, { taskItems.removeAt(from) })
        listener?.onRemoveItem()
    }

    fun removeAllItems() {
        while (size > 0) {
            taskItems.removeAt(size - 1)
        }
    }

    fun refreshTasks() {
        removeAllItems()
        fetchTasks()
    }

    fun storeLastItem(index: Int) {
        lastItem = Pair(index, taskItems[index])
    }

    fun restoreLastItems() {
        lastItem?.let {
            saveTask(it.second.task.get(), {
                taskItems.add(it.first, it.second)
            })
        }
    }

    fun addObservableListCallBack(callback: OnListChangedCallback<ObservableList<TaskViewModel>>) {
        taskItems.addOnListChangedCallback(callback)
        observableListCallback = callback
    }

    fun removeObservableListCallback() {
        observableListCallback?.let { taskItems.removeOnListChangedCallback(it) }
    }

    /**
     * ListenerBinding
     */
    fun onClickFAB(view: View) {
        listener?.onClickFAB()
    }

    override fun onClickItem(task: Task) {
        listener?.onClickItem(task)
    }

    override fun onCleared() {
        super.onCleared()
        listener = null
        compositeDisposable.clear()
        removeObservableListCallback()
    }

}
