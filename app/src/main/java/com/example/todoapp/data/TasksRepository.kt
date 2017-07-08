package com.example.todoapp.data

import com.example.todoapp.model.Task
import com.example.todoapp.util.DateUtil

/**
 * Created by Yasuhiro Suzuki on 2017/06/18.
 */
object TasksRepository {

    fun getTasks(): List<Task> {
        val tasks = arrayListOf<Task>()
        tasks.add(Task(id = 1, date = DateUtil.currentDate))
        tasks.add(Task(id = 2, date = DateUtil.currentDate))
        tasks.add(Task(id = 3, date = DateUtil.currentDate))
        tasks.add(Task(id = 4, date = DateUtil.currentDate))
        tasks.add(Task(id = 5, date = DateUtil.currentDate))
        tasks.add(Task(id = 6, date = DateUtil.currentDate))
        tasks.add(Task(id = 7, date = DateUtil.currentDate))
        tasks.add(Task(id = 8, date = DateUtil.currentDate))
        tasks.add(Task(id = 9, date = DateUtil.currentDate))
        return tasks
    }

}