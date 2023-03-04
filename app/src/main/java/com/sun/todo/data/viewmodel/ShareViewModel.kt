package com.sun.todo.data.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.graphics.Color
import android.graphics.Color.red
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.sun.todo.R
import com.sun.todo.data.model.Priority
import com.sun.todo.data.model.ToDoData

class ShareViewModel(application: Application):AndroidViewModel(application) {

    val emptyData:MutableLiveData<Boolean> = MutableLiveData(false)

    fun checkEmptyData(datalist:List<ToDoData>){
        emptyData.value = datalist.isEmpty()
    }

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

        }

    }

    fun toPriority(priority: String): Priority {
        Log.d("ShareViewModel","toPriority")
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

    fun verification(title: String, description: String):Boolean {
        return if(title.isEmpty()||description.isEmpty()){
            false
        }else !(title.isEmpty()||description.isEmpty())
    }

}