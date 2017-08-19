package com.example.todoapp.di

import com.example.todoapp.ui.edit.EditTaskActivity
import com.example.todoapp.ui.tasks.TasksActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Yasuhiro Suzuki on 2017/08/16.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class, AppDatabaseModule::class))
interface AppDatabaseComponent {

    fun inject(activity: TasksActivity)

    fun inject(activity: EditTaskActivity)

}