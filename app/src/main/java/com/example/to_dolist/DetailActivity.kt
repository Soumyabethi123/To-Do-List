package com.example.to_dolist

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailActivity : AppCompatActivity() {

    lateinit var list : TaskList

    lateinit var taskRecyclerView: RecyclerView

    lateinit var addtaskbutton : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        list= intent.getParcelableExtra<TaskList>(MainActivity.INTENT_LIST_KEY) as TaskList
        title=list.name

        taskRecyclerView = findViewById(R.id.taskListRecyclerView)
        taskRecyclerView.layoutManager=LinearLayoutManager(this)
        taskRecyclerView.adapter=TaskAdapter(this,list)

        addtaskbutton = findViewById(R.id.add_task_button)
        addtaskbutton.setOnClickListener{
            showCreateTaskDialog()
        }
    }

    override fun onBackPressed() {

        val bundle = Bundle()
        bundle.putParcelable(MainActivity.INTENT_LIST_KEY,list)

        val intent = Intent()
        intent.putExtras(bundle)
        setResult(Activity.RESULT_OK,intent)
        super.onBackPressed()
    }

    private fun showCreateTaskDialog() {

        val dialogtitle = "Enter Your to do list "
        val positivebutton = "Create"

        val message = EditText(this)
        message.inputType = InputType.TYPE_CLASS_TEXT

        val mydialog = AlertDialog.Builder(this)

        mydialog.setTitle(dialogtitle)
        mydialog.setView(message)
        mydialog.setPositiveButton(positivebutton){
                dialog,_ ->
              val task = message.text.toString()
             list.tasks.add(task)
            dialog.dismiss()

        }
        mydialog.create().show()
    }
}