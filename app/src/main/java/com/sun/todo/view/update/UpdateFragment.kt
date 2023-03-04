package com.sun.todo.view.update

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sun.todo.R
import com.sun.todo.data.model.ToDoData
import com.sun.todo.data.viewmodel.ShareViewModel
import com.sun.todo.data.viewmodel.TodoViewModel
import com.sun.todo.databinding.FragmentUpdateBinding


class UpdateFragment : Fragment() {

    private val avg by navArgs<UpdateFragmentArgs>()

    var binding:FragmentUpdateBinding? = null

    private val shareViewModel:ShareViewModel by viewModels()

    private val todoViewModel:TodoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)
        binding?.titleUpdate?.setText(avg.currentItem.title)
        binding?.descriptionUpdate?.setText(avg.currentItem.description)
        toIntPriority(avg.currentItem.priority.name)?.let {
            binding?.priorityUpdate?.onItemSelectedListener = shareViewModel.listener
            binding?.priorityUpdate?.setSelection(it)
        }
        return binding!!.root
    }

    private fun toIntPriority(description: String): Int? {
        var i:Int?=11
        when(description){
            "HIGH"->{
                i=0
            }
            "MIDDLE"->{
                i=1
            }
            "LOW"->{
                i=2
            }
        }
        return i
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_menu,menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("UpdateFragment","onOptionsItemSelected")
        when(item.itemId){
            R.id.ok_update -> updateTodoData()
            R.id.delete_update->  deleteTodo()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteTodo() {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setPositiveButton("是") { a, z ->
            todoViewModel.deleteById(avg.currentItem.id)
            Toast.makeText(context, "删除记录成功！", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment2)
        }

        alertDialog.setNegativeButton("否"){
            z,a-> Toast.makeText(context, "取消删除", Toast.LENGTH_SHORT).show()
        }
        alertDialog.setTitle("删除todo")
        alertDialog.setMessage("你确定删除${avg.currentItem.title}?")
        alertDialog.create().show()
    }

    private fun updateTodoData() {
        val title = binding?.titleUpdate?.text.toString()
        val priority = shareViewModel.toPriority(binding?.priorityUpdate?.selectedItem.toString())
        val description = binding?.descriptionUpdate?.text.toString()
        val todoDao = ToDoData(
            avg.currentItem.id,
            title,
            priority,
            description
        )
        if(shareViewModel.verification(title, description)){
            todoViewModel.update(todoDao)
            Toast.makeText(context,"更新成功",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment2)
        }else{
            Toast.makeText(context,"请检查title和description是否输入！",Toast.LENGTH_SHORT).show()
        }

    }

}


