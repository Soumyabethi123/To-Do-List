package com.example.to_dolist

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.to_dolist.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() , TodoListFragment.OnFragmentInteractionListener{


    lateinit var binding: ActivityMainBinding

    private var todoListFragment = TodoListFragment.newInstance()

    companion object{
        const val INTENT_LIST_KEY = "list"
        const val LIST_DETAIL_REQUEST_CODE = 123
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->

             addtodolist()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addtodolist(){

        val dialogtitle = "Enter Your to do list "
        val positivebutton = "Create"
        val message = EditText(this)
        message.inputType = InputType.TYPE_CLASS_TEXT

        val mydialog = AlertDialog.Builder(this)

        mydialog.setTitle(dialogtitle)
        mydialog.setView(message)
        mydialog.setPositiveButton(positivebutton){
            dialog,_ ->
            val list = TaskList(message.text.toString())
            todoListFragment.addList(list)
            dialog.dismiss()
            showTaskList(list)
        }
        mydialog.create().show()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == LIST_DETAIL_REQUEST_CODE){

            data?.let{

                val list = data.getParcelableExtra<TaskList>(INTENT_LIST_KEY)!!
                todoListFragment.saveList(list)
            }
        }
    }



    private fun showTaskList(list : TaskList){

        val taskListItem = Intent(this,DetailActivity::class.java)
        taskListItem.putExtra(INTENT_LIST_KEY,list)
        startActivityForResult(taskListItem, LIST_DETAIL_REQUEST_CODE)

    }

    override fun onTodoListClicked(list: TaskList) {

        showTaskList(list)
    }
}