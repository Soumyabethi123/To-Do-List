package com.example.to_dolist

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TodoListFragment : Fragment() , Adapter.TodoListClickListener{

    private lateinit var todoRecyclerView: RecyclerView

    private lateinit var listDataManager : ListDataManager

    private var listener : OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lists = listDataManager.readList()
        todoRecyclerView=view.findViewById(R.id.list_recyclerview)
        todoRecyclerView.layoutManager= LinearLayoutManager(activity)
        todoRecyclerView.adapter=Adapter(lists,this)

    }

    override fun onAttach(context: Context){

        super.onAttach(context)
        if(context is OnFragmentInteractionListener){
            listener=context
            listDataManager = ListDataManager(context)
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
    interface OnFragmentInteractionListener{

        fun onTodoListClicked(list: TaskList)
    }

    companion object {

        fun newInstance() : TodoListFragment{
            return TodoListFragment()
        }
    }

    override fun listItemClicked(list: TaskList) {
        listener?.onTodoListClicked(list)
    }

    fun addList(list: TaskList) {

        listDataManager.SaveList(list)
        val todoAdapter = todoRecyclerView.adapter as Adapter
        todoAdapter.add(list)
    }

    fun saveList(list: TaskList) {

        listDataManager.SaveList(list)
        updateLists()
    }

    private fun updateLists() {

        val lists = listDataManager.readList()
        todoRecyclerView.adapter = Adapter(lists,this)
    }
}