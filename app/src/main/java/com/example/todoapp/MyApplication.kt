package com.example.todoapp

import android.app.Application
import com.example.todoapp.di.AppDatabaseComponent
import com.example.todoapp.di.AppDatabaseModule
import com.example.todoapp.di.ApplicationModule
import com.example.todoapp.di.DaggerAppDatabaseComponent

/**
 * Created by Yasuhiro Suzuki on 2017/06/11.
 */
class MyApplication : Application() {

    companion object {
        lateinit var appDatabaseComponent: AppDatabaseComponent
    }

    override fun onCreate() {
        super.onCreate()
        initComponents()
    }

    fun initComponents() {
        appDatabaseComponent = DaggerAppDatabaseComponent.builder()
                .applicationModule(ApplicationModule(this))
                .appDatabaseModule(AppDatabaseModule())
                .build()
    }

}