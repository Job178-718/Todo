package com.sun.todo.data;

import android.util.Log
import android.view.View
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.sun.todo.R
import com.sun.todo.data.model.Priority

class BindAdapter {
    companion object{
        @BindingAdapter("android:emptyNoData")
        @JvmStatic
        fun emptyNoData(view: View, emptyNotData:MutableLiveData<Boolean>){
            when(emptyNotData.value){
                true->{view.visibility = View.VISIBLE}
                false->{view.visibility = View.INVISIBLE}
                else -> {
                    view.visibility = View.INVISIBLE
                }
            }
        }

        @BindingAdapter("android:enterAddFragment")
        @JvmStatic
        fun onEnterAddFragment(view:View,boolean: Boolean){
            view.setOnClickListener {
                if(boolean){
                    view.findNavController().navigate(R.id.action_listFragment_to_addFragment)
                }
            }
        }

        @BindingAdapter("android:cardBackGround")
        @JvmStatic
        fun cardBackGround(view:CardView,priority: Priority){
            when(priority){
                Priority.HIGH->{view.setCardBackgroundColor(ContextCompat.getColor(view.context,R.color.red))}
                Priority.MIDDLE->{view.setCardBackgroundColor(ContextCompat.getColor(view.context,R.color.yellow))}
                Priority.LOW->{view.setCardBackgroundColor(ContextCompat.getColor(view.context,R.color.green))}
                else -> {
                    view.setCardBackgroundColor(ContextCompat.getColor(view.context,R.color.green))
                }
            }
        }
    }

}
