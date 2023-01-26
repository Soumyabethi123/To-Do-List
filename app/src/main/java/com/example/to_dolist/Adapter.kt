package com.example.to_dolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class Adapter(val lists : ArrayList<TaskList>) : RecyclerView.Adapter<Viewholder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_list_viewholder,parent,false)
        return Viewholder(view)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {

        holder.listPositionTextView.text = (position + 1).toString()
        holder.listTitleTextView.text = lists[position].name
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    fun add(message: TaskList) {

        lists.add(message)
        notifyItemInserted(lists.size-1)
    }


}