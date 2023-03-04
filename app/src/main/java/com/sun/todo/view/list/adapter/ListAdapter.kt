package com.sun.todo.view.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sun.todo.data.model.ToDoData
import com.sun.todo.databinding.RecyclerListBinding
import com.sun.todo.view.update.UpdateFragmentArgs

class ListAdapter: RecyclerView.Adapter<ListAdapter.VH>() {

    var listData = emptyList<ToDoData>()

    private var onClickItem:OnClickItem? = null

    class VH(private val binding: RecyclerListBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(toDoData: ToDoData){
            binding.todoData = toDoData
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent:ViewGroup): VH {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecyclerListBinding.inflate(layoutInflater,parent,false)
                return VH(binding)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
       return VH.from(parent)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val todo = listData[position]
        holder.bind(todo)
        holder.itemView.setOnClickListener {
            if(onClickItem!=null){
                onClickItem!!.setOnClickItem(position,todo)
            }
        }

    }

    override fun getItemCount(): Int {
        return listData.size
    }

    fun setData(listTodo: List<ToDoData>){
        this.listData = listTodo
        notifyDataSetChanged()
    }

     interface OnClickItem{
        fun setOnClickItem(position:Int,toDoData: ToDoData)
    }

    fun setOnClickItem(onClickItem:OnClickItem){
        this.onClickItem = onClickItem
    }

}