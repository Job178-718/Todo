package com.sun.todo.data.model

import androidx.room.TypeConverter


class Conversion {

    @TypeConverter
    fun fromPriority(priority: Priority):String{
        return toChinese(priority.name)
    }

    @TypeConverter
    fun toPriority(priority: String): Priority {
        return Priority.valueOf(priority)
    }


    private fun toChinese(name: String): String {
        var c:String? = null
        when(name){
            "HIGH"->{
                c = "高"
            }
            "MIDDLE"->{
                c = "中"
            }
            "LOW"->{
                c = "低级"
            }
        }
        return c!!
    }
}