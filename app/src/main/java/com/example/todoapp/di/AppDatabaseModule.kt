package com.example.todoapp.di

import android.arch.persistence.room.Room
import android.content.Context
import com.example.todoapp.data.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Yasuhiro Suzuki on 2017/08/16.
 *
 */
@Module
class AppDatabaseModule {

    @Provides
    @Singleton
    fun getAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "sample.db").build()
    }

}