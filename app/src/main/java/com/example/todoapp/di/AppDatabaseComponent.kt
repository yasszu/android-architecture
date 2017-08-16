package com.example.todoapp.di

import com.example.todoapp.ui.edit.EditTaskViewModel
import com.example.todoapp.ui.tasks.TasksViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Yasuhiro Suzuki on 2017/08/16.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class, AppDatabaseModule::class))
interface AppDatabaseComponent {
    fun inject(viewModel: TasksViewModel)
    fun inject(viewModel: EditTaskViewModel)
}