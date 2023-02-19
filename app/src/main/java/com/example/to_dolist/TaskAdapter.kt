package com.example.to_dolist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(val context: Context, private var list: TaskList) : RecyclerView.Adapter<TaskAdapter.taskViewholder>() {

    class taskViewholder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var taskposition = itemView.findViewById<TextView>(R.id.text1)
        var taskmsg = itemView.findViewById<TextView>(R.id.text2)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): taskViewholder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.taskviewholder,parent,false)
        return taskViewholder(view)
    }

    override fun getItemCount(): Int {

        return list.tasks.size
    }

    override fun onBindViewHolder(holder: taskViewholder, position: Int) {

        holder.taskposition.text = (position + 1).toString()
        holder.taskmsg.text = list.tasks[position]
    }


}