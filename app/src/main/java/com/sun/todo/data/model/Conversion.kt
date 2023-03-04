package com.sun.todo.data.model

import android.util.Log
import androidx.room.TypeConverter


class Conversion {

    @TypeConverter
    fun fromPriority(priority: Priority):String{
        return priority.name
    }

    @TypeConverter
    fun toPriority(priority: String): Priority {
        Log.w("Conversion","$priority")

        return toEnglish(priority)
    }


    fun toEnglish(priority: String): Priority {
        var p: Priority? = null
        when(priority){
            "高"->{p= Priority.HIGH}
            "中"->{p= Priority.MIDDLE}
            "低"->{p= Priority.LOW}
            "HIGH"->{p= Priority.HIGH}
            "MIDDLE"->{p=Priority.MIDDLE}
            "LOW"->{p=Priority.LOW}
        }
        return p!!
    }


}