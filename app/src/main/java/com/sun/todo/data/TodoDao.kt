package com.sun.todo.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sun.todo.data.model.ToDoData

@Dao
interface TodoDao {
    @Query("select * from todo order by id ASC ")
    fun getAllData():LiveData<List<ToDoData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE) //TODO:协程使用
    fun insertTodo(toDoData: ToDoData)
}