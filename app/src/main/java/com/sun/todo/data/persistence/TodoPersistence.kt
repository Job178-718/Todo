package com.sun.todo.data.persistence

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.sun.todo.data.TodoDao
import com.sun.todo.data.model.ToDoData

class TodoPersistence(private val todoDao: TodoDao) {

    val getAllData:LiveData<List<ToDoData>> = todoDao.getAllData()

    fun insertTodo(toDoData: ToDoData) {
        todoDao.insertTodo(toDoData)
    }

    fun updateTodo(toDoData: ToDoData) {
        todoDao.updateTodo(toDoData)
    }

    fun deleteTodo(id:Int){
        todoDao.deleteByIdTodo(id)
    }

    fun deleteAllTodo() {
        todoDao.deleteAllTodo()
    }

    fun queryTodoList(query: String):LiveData<List<ToDoData>>{
        return todoDao.queryTodo(query)
    }

    fun sortRiseListTodo():LiveData<List<ToDoData>>{
        return todoDao.sortRiseTodoList()
    }

    fun sortDeclineListTodo():LiveData<List<ToDoData>>{
        return todoDao.sortDeclineTodoList()
    }
}