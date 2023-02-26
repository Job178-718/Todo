package com.sun.todo.data.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.graphics.Color
import android.graphics.Color.red
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import com.sun.todo.R
import com.sun.todo.data.model.Priority

class ShareViewModel(application: Application):AndroidViewModel(application) {

    val listener: AdapterView.OnItemSelectedListener = object :AdapterView.OnItemSelectedListener{
        @SuppressLint("ResourceType")
        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long) {
            when(position){
                0->{(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application,R.color.red))}
                1->{(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.yellow))}
                2->{(parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.green))}
            }
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            TODO("Not yet implemented")
        }

    }

    fun toPriority(priority: String): Priority {
        var p: Priority? = null
        Log.d("AddFragment","$priority")
        when(priority){
            "高"->{p= Priority.HIGH}
            "中"->{p= Priority.MIDDLE}
            "低"->{p= Priority.LOW}
        }
        return p!!
    }

    fun verification(title: String, description: String):Boolean {
        return if(title.isEmpty()||description.isEmpty()){
            false
        }else !(title.isEmpty()||description.isEmpty())
    }

}