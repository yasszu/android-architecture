package com.example.todoapp.data

import android.arch.persistence.room.Room
import android.content.Context
import com.example.todoapp.model.Task
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

/**
 * Created by Yasuhiro Suzuki on 2017/06/18.
 */
object TasksRepository {

    lateinit var database: ToDoAppDatabase

    fun initialize(context: Context) {
        database = Room.databaseBuilder(
                context.applicationContext,
                ToDoAppDatabase::class.java, "sample.db").build()
    }

    fun saveTask(task: Task): Completable = Completable
            .fromAction({ database.tasksDao().insert(task) })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun deleteTask(id: String): Completable = Completable
            .fromAction({ database.tasksDao().delete(id) })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun deleteAllTasks(): Completable = Completable
            .fromAction({ database.tasksDao().deleteAll() })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getTasks(): Single<List<Task>> = database
            .tasksDao()
            .findAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

}