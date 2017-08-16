package com.example.todoapp.di

import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Created by Yasuhiro Suzuki on 2017/08/16.
 *
 * Context dependency
 */
@Module
class ApplicationModule(val context: Context) {

    @Provides
    fun provideContext(): Context {
        return context
    }

}