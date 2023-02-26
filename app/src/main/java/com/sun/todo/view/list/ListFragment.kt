package com.sun.todo.view.list

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.NavHostController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.sun.todo.R
import com.sun.todo.databinding.FragmentListBinding


class ListFragment : Fragment() {

    private var binding:FragmentListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentListBinding.inflate(layoutInflater, container, false)
        Log.d("ListFragment","createView")

        //1.实现添加新事件的功能
        binding?.floatButton?.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        //2.测试用
        binding?.listFragment?.setOnClickListener{
            Log.d("ListFragment","sssssss")
            findNavController().navigate(R.id.action_listFragment_to_updateFragment)
        }

        setHasOptionsMenu(true)
        return binding?.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_list,menu)
    }

}