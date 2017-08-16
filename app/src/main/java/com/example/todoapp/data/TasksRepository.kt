package com.example.todoapp.data

import com.example.todoapp.model.Task
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Yasuhiro Suzuki on 2017/06/18.
 */
@Singleton
class TasksRepository @Inject constructor(val database: AppDatabase) {

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