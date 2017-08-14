package com.example.todoapp.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

/**
 * Created by Yasuhiro Suzuki on 2017/06/18.
 */
@Entity(tableName = "tasks")
data class Task(
        @PrimaryKey val id: String = UUID.randomUUID().toString(),
        val date: String,
        val title: String = "",
        val description: String = "",
        val completed: Boolean = false
)