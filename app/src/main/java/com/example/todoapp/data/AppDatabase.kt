package com.example.todoapp.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.todoapp.model.Task

/**
 * Created by Yasuhiro Suzuki on 2017/08/08.
 */
@Database(entities = arrayOf(Task::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun tasksDao(): TasksDao

}