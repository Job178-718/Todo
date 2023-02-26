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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application):AndroidViewModel(application) {



    private val db:TodoDao
    private var todoPersistence:TodoPersistence
    private var allData:LiveData<List<ToDoData>>

    init {
        db = TodoDataBase.getDataBase(application.applicationContext).getDao()
        todoPersistence= TodoPersistence(db)
        allData= todoPersistence?.getAllData!!
    }

    fun insert(toDoData: ToDoData){
        //TODO：viewModelScope作用
        viewModelScope.launch (Dispatchers.IO){
            todoPersistence?.insertToDo(toDoData)
        }
    }

}