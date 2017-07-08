package com.example.todoapp.model

/**
 * Created by Yasuhiro Suzuki on 2017/06/18.
 */
data class Task(
        val id: Long,
        val date: String,
        val title: String = "",
        val description: String = "",
        val completed: Boolean = false
)