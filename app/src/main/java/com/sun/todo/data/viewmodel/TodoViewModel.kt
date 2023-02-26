package com.sun.todo.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sun.todo.data.TodoDao
import com.sun.todo.data.TodoDataBase
import com.sun.todo.data.model.ToDoData
import com.sun.todo.data.persistence.TodoPersistence
import kotlinx.coroutines.launch

class TodoViewModel(application: Application):AndroidViewModel(application) {
    val db:TodoDao = TodoDataBase.getDataBase(application.applicationContext).getDao()
    var todoPersistence:TodoPersistence? = null
    var allData:LiveData<List<ToDoData>>? = null

    init {
       todoPersistence = TodoPersistence(db)
       todoPersistence?.getAllData
    }

    suspend fun insert(toDoData: ToDoData){
        viewModelScope.launch {
            todoPersistence?.insertToDo(toDoData)
        }
    }

}