package com.example.todoapp.ui.tasks

import android.databinding.ObservableField
import android.view.View
import com.example.todoapp.model.Task
import java.lang.ref.WeakReference

/**
 * Created by Yasuhiro Suzuki on 2017/07/08.
 */
class TaskViewModel(task: Task){

    val task = ObservableField<Task>()

    var navigator: WeakReference<TaskNavigator>? = null

    init {
        this.task.set(task)
    }

    fun setNavigator(navigator: TaskNavigator) {
        this.navigator = WeakReference(navigator)
    }

    /** Listener Binding */
    fun onClickItem(view: View) {
        navigator?.get()?.onClickItem(task.get())
    }

}