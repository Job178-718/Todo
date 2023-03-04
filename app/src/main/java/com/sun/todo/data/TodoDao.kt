package com.sun.todo.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.sun.todo.data.model.ToDoData

@Dao
interface TodoDao {

    @Query("select * from todo order by id ASC ")
    fun getAllData():LiveData<List<ToDoData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE) //TODO:协程使用
    fun insertTodo(toDoData: ToDoData)

    @Update
    fun updateTodo(toDoData: ToDoData)

    @Query("delete from todo where id=:id")
    fun deleteByIdTodo(id:Int)

    @Query("delete from todo")
    fun deleteAllTodo()

    @Query("select * from todo where title like:query")
    fun queryTodo(query: String):LiveData<List<ToDoData>>

    @Query("select * from todo order by case when priority like '%H%' then 1 when priority like '%M%' then 2 when priority like '%L%' then 3  end")
    fun sortRiseTodoList():LiveData<List<ToDoData>>

    @Query("select * from todo order by case when priority like '%L%' then 1 when priority like '%M%' then 2 when priority like '%H%' then 3 end")
    fun sortDeclineTodoList():LiveData<List<ToDoData>>
}