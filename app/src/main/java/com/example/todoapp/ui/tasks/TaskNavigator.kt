package com.example.todoapp.ui.tasks

import com.example.todoapp.model.Task

/**
 * Created by Yasuhiro Suzuki on 2017/07/08.
 */
interface TaskNavigator {

    fun onClickItem(task: Task)

}