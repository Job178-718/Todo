package com.sun.todo.data.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import com.sun.todo.data.TodoDao
import com.sun.todo.data.TodoDataBase
import com.sun.todo.data.model.ToDoData
import com.sun.todo.data.persistence.TodoPersistence
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application):AndroidViewModel(application) {

    private val db:TodoDao

    private var todoPersistence:TodoPersistence

    var allData:LiveData<List<ToDoData>>

    var sortDeclineListTodo:LiveData<List<ToDoData>>

    var sortRiseListTodo:LiveData<List<ToDoData>>

    init {
        db = TodoDataBase.getDataBase(application.applicationContext).getDao()
        todoPersistence= TodoPersistence(db)
        allData= todoPersistence?.getAllData!!
        sortDeclineListTodo = todoPersistence.sortDeclineListTodo()
        sortRiseListTodo = todoPersistence.sortRiseListTodo()
        Log.d("TodoViewModel","init")
    }

    fun insert(toDoData: ToDoData){
        //TODO：viewModelScope作用
        viewModelScope.launch (Dispatchers.IO){
            todoPersistence?.insertTodo(toDoData)
        }
    }

    fun update(toDoData: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            todoPersistence.updateTodo(toDoData)
        }
    }


    fun deleteById(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            todoPersistence.deleteTodo(id)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            todoPersistence.deleteAllTodo()
        }
    }


    fun queryTodoList(query: String):LiveData<List<ToDoData>>{
        return todoPersistence.queryTodoList(query)
    }


}