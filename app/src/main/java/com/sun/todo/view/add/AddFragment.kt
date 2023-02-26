package com.sun.todo.view.add

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sun.todo.R
import com.sun.todo.data.model.Priority
import com.sun.todo.data.model.ToDoData
import com.sun.todo.data.viewmodel.ShareViewModel
import com.sun.todo.data.viewmodel.TodoViewModel
import com.sun.todo.databinding.FragmentAddBinding


@Suppress("OverrideDeprecatedMigration")
class AddFragment : Fragment() {

    private var bindind: FragmentAddBinding? = null

    private val todoViewModel:TodoViewModel by viewModels()

    private val shareViewModel:ShareViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindind = FragmentAddBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)
        bindind!!.priorityAdd.onItemSelectedListener = shareViewModel.listener
        return bindind!!.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_add,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.ok_add ->{
                insertTodo()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertTodo() {
        val title = bindind!!.titleAdd.text.toString()
        val priority = bindind!!.priorityAdd.selectedItem.toString()
        val description = bindind!!.descriptionAdd.text.toString()
        val verification = shareViewModel.verification(title,description)
        if(verification){
           val newTodo:ToDoData = ToDoData(
               0,title,shareViewModel.toPriority(priority),description
           )
            todoViewModel.insert(newTodo)
            Toast.makeText(context,"添加完成",Toast.LENGTH_SHORT)
                .show()
        }else{
            Toast.makeText(context,"请填充所有的字段",Toast.LENGTH_SHORT)
                .show()
        }
    }




}