package com.example.to_dolist

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


class MainActivity : AppCompatActivity() {

    lateinit var todoRecyclerView: RecyclerView
    lateinit var binding: ActivityMainBinding

    val listDataManager : ListDataManager = ListDataManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lists = listDataManager.readList()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        todoRecyclerView=findViewById(R.id.list_recyclerview)

        todoRecyclerView.layoutManager=LinearLayoutManager(this)
        todoRecyclerView.adapter=Adapter(lists)

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

            val adapter = todoRecyclerView.adapter as Adapter
            val list = TaskList(message.text.toString())

            listDataManager.SaveList(list)
            adapter.add(list)

            dialog.dismiss()
        }
        mydialog.create().show()

    }
}