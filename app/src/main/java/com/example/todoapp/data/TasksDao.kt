package com.example.todoapp.data

import android.arch.persistence.room.*
import com.example.todoapp.model.Task
import io.reactivex.Single

/**
 * Created by Yasuhiro Suzuki on 2017/08/08.
 */
@Dao
interface TasksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Delete
    fun delete(task: Task)

    @Query("DELETE FROM tasks")
    fun deleteAll()

    @Query("SELECT * FROM tasks WHERE id = :id")
    fun findById(id: String): Single<Task>

    @Query("SELECT * FROM tasks")
    fun findAll(): Single<List<Task>>

}