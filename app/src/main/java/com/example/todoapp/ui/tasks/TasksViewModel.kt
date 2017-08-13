package com.example.todoapp.ui.tasks

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import android.databinding.ObservableList.OnListChangedCallback
import android.util.Log
import android.view.View
import com.example.todoapp.data.TasksRepository
import com.example.todoapp.model.Task
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Yasuhiro Suzuki on 2017/06/18.
 */
class TasksViewModel: ViewModel(), TaskNavigator {

    interface Listener {
        fun onRemoveItem()
        fun onClickFAB()
    }

    val compositeDisposable = CompositeDisposable()

    val taskItems: ObservableList<TaskViewModel> = ObservableArrayList()

    val size: Int
        get() = taskItems.size

    var lastItem: Pair<Int, TaskViewModel>? = null

    var observableListCallback: OnListChangedCallback<ObservableList<TaskViewModel>>? = null

    var listener: Listener? = null

    init {
    }

    fun fetchTasks() {
        val disposable = TasksRepository
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
        TasksRepository
                .deleteAllTasks()
                .subscribe(
                        { removeAllItems() },
                        { it.printStackTrace() })
    }

    fun refreshTasks() {
        removeAllItems()
        fetchTasks()
    }

    fun removeItem(from: Int) {
        storeLastItem(from)
        taskItems.removeAt(from)
        listener?.onRemoveItem()
    }

    fun removeAllItems() {
        do {
            taskItems.removeAt(size - 1)
        } while (size > 0)
    }

    fun storeLastItem(index: Int) {
        lastItem = Pair(index, taskItems[index])
    }

    fun restoreLastItems() {
        lastItem?.let { taskItems.add(it.first, it.second) }
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
        Log.d("TaskNavigator", task.id)
    }

    override fun onCleared() {
        super.onCleared()
        listener = null
        compositeDisposable.clear()
        removeObservableListCallback()
    }

}
