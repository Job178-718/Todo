package com.sun.todo.data.model

import androidx.room.TypeConverter


class Conversion {

    @TypeConverter
    fun fromPriority(priority: Priority):String{
        return priority.name;
    }

    @TypeConverter
    fun toPriority(priority: String): Priority {
        return Priority.valueOf(priority)
    }
}