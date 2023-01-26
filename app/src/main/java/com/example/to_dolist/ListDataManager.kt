package com.example.to_dolist

import android.content.Context
import androidx.preference.PreferenceManager


class ListDataManager(private val context: Context) {

    fun SaveList(list : TaskList){

        val sharedprefs = PreferenceManager.getDefaultSharedPreferences(context).edit()

        sharedprefs.putStringSet(list.name,list.tasks.toHashSet())

        sharedprefs.apply()
    }

    fun readList() : ArrayList<TaskList>{

        val sharedprefs = PreferenceManager.getDefaultSharedPreferences(context)

        val contents = sharedprefs.all

        val tasklists = ArrayList<TaskList>()

        for(tasklist in contents){

            val taskItems = ArrayList(tasklist.value as HashSet<String>)

            val list = TaskList(tasklist.key,taskItems)

            tasklists.add(list)
        }

        return tasklists
    }
}