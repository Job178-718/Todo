package com.sun.todo.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "todo")
@Parcelize
data class ToDoData(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val title:String,
    val priority: Priority,
    val description:String
):Parcelable