package com.sun.todo.data.persistence

import androidx.lifecycle.LiveData
import com.sun.todo.data.TodoDao
import com.sun.todo.data.model.ToDoData

class TodoPersistence(private val todoDao: TodoDao) {

    //TODO:用法奇特
    val getAllData:LiveData<List<ToDoData>> = todoDao.getAllData()

    suspend fun insertToDo(toDoData: ToDoData) {
        todoDao.insertTodo(toDoData)
    }

}