package com.example.todoapp

import android.app.Application
import io.realm.Realm

/**
 * Created by Yasuhiro Suzuki on 2017/06/11.
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeServices()
    }

    private fun initializeServices() {
        Realm.init(this)
    }

}