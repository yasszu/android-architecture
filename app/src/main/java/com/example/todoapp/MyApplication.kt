package com.example.todoapp

import android.app.Application
import com.example.todoapp.data.TasksRepository

/**
 * Created by Yasuhiro Suzuki on 2017/06/11.
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        TasksRepository.initialize(this)
    }
}