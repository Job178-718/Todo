package com.sun.todo.view.list

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.sun.todo.R
import com.sun.todo.data.model.ToDoData
import com.sun.todo.data.viewmodel.ShareViewModel
import com.sun.todo.data.viewmodel.TodoViewModel
import com.sun.todo.databinding.FragmentListBinding
import com.sun.todo.view.list.adapter.ListAdapter
import jp.wasabeef.recyclerview.animators.LandingAnimator


class ListFragment : Fragment(), SearchView.OnQueryTextListener{


    private var adapter: ListAdapter? = null
    private var recycler:RecyclerView? = null
    private var binding: FragmentListBinding? = null
    private val todoViewModel:TodoViewModel by viewModels()
    private val shareViewModel:ShareViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(layoutInflater, container, false)
        binding?.mShareViewModel = shareViewModel
        binding?.lifecycleOwner = this
        recyclerView()
        todoViewModel.allData.observe(viewLifecycleOwner, Observer {
            shareViewModel.checkEmptyData(it)
            adapter?.setData(it)
        })
        setHasOptionsMenu(true)
        return binding?.root
    }

    private fun recyclerView() {
        adapter = ListAdapter()
        recycler = binding!!.recyclerView
        recycler!!.layoutManager = GridLayoutManager(requireContext(),2,GridLayoutManager.VERTICAL,false)
        recycler!!.itemAnimator = LandingAnimator().apply {
            addDuration = 300
        }
        recycler!!.adapter = adapter
        adapter?.setOnClickItem(object :ListAdapter.OnClickItem{
            override fun setOnClickItem(position:Int,toDoData: ToDoData){
                //通过args传递参数
                val action = ListFragmentDirections.actionListFragmentToUpdateFragment(toDoData)
                findNavController().navigate(action)
            }
        })
        //滑动删除功能!!
        swipeToDelete(recycler!!)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_list,menu)
        val searchView = MenuItemCompat.getActionView(menu.findItem(R.id.search)) as SearchView
        searchView.setOnQueryTextListener(this)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.clear_all-> deleteAll()
            R.id.decline-> {
                Log.d("ListFragment","decline......")
                todoViewModel.sortDeclineListTodo.observe(this)
                {
                    adapter!!.setData(it)
                }
            }
            R.id.rise-> {
                Log.d("ListFragment","rise....")
                todoViewModel.sortRiseListTodo.observe(this)
                {
                    adapter!!.setData(it)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun swipeToDelete(recyclerView: RecyclerView){
      val swipeToDelete = object: SwipeToDelete() {
          override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
              val toData = adapter!!.listData[viewHolder.position]
              todoViewModel.deleteById(toData.id)
              Toast.makeText(context, "删除记录", Toast.LENGTH_SHORT).show()
              //撤销删除
              restoreDeleteItem(viewHolder.itemView,toData,viewHolder.adapterPosition)
          }
      }
      val helpers = ItemTouchHelper(swipeToDelete)
      helpers.attachToRecyclerView(recyclerView)
    }


    fun restoreDeleteItem(view:View,toDoData: ToDoData,position: Int){
         Snackbar
             .make(view,"删除${toDoData.title}",Snackbar.LENGTH_LONG)
             .setAction("撤销"){
                 todoViewModel.insert(toDoData)
                 adapter?.notifyItemChanged(position)
             }.show()
    }

    private fun deleteAll() {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setMessage("是否删除所有内容?")
        alertDialog.setTitle("删除全部")
        alertDialog.setPositiveButton("是"){ _,_->
            todoViewModel.deleteAll()
            Toast.makeText(context,"删除所有数据成功",Toast.LENGTH_SHORT).show()

        }
        alertDialog.setNegativeButton("否"){ _,_->
            Toast.makeText(context,"",Toast.LENGTH_SHORT).show()
        }
        alertDialog.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(query!=null){
           searchQueryTodo(query)
        }
       return true
    }

    private fun searchQueryTodo(query: String) {
        todoViewModel.queryTodoList("%$query%").observe(this){
            adapter!!.setData(it)
        }
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if(newText!=null){
            searchQueryTodo(newText)
        }
        return true
    }

}