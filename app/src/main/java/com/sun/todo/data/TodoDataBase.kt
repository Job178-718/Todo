package com.sun.todo.data

import android.content.Context
import androidx.room.*
import com.sun.todo.data.model.Priority
import com.sun.todo.data.model.ToDoData

@Database(entities = [ToDoData::class], version = 1, exportSchema = false)
@TypeConverters(value = [Priority::class])
abstract class TodoDataBase:RoomDatabase() {


    abstract fun getDao():TodoDao

    companion object{

         private var INSTANCE:TodoDataBase? = null

         fun getDataBase(context: Context):TodoDataBase{
             var database = INSTANCE;
             if(database!=null){return database
             }
             synchronized(this){
                 val room:TodoDataBase = Room.databaseBuilder(
                     context.applicationContext,
                     TodoDataBase::class.java,
                     "todo_database")
                     .build()
                 INSTANCE = room
                 return INSTANCE as TodoDataBase
             }
         }
    }
}