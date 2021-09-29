package com.example.todoapp

import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var todo_items: ArrayList<ToDoItem>
    private lateinit var my_rv_Adapter: RecyclerViewAdapter
    private lateinit var rvItems: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todo_items = arrayListOf()

        rvItems = findViewById(R.id.rvMain)
        my_rv_Adapter = RecyclerViewAdapter(todo_items)
        rvItems.adapter = my_rv_Adapter
        rvItems.layoutManager = LinearLayoutManager(this)


        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        fab.setOnClickListener {
            ToDoDialog()
        }
    }

    private fun ToDoDialog(){
        val dialogBuilder = AlertDialog.Builder(this)

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL

        val input = EditText(this)
        input.hint = "Enter to-do item"
        layout.addView(input)

        dialogBuilder.setPositiveButton("ADD", DialogInterface.OnClickListener {
                    dialog, id -> todo_items.add(ToDoItem(input.text.toString()))
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })

        val alert = dialogBuilder.create()
        alert.setTitle("New Item")
        alert.setView(layout)
        alert.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var itemsDeleted = 0
        for(i in todo_items){
            if(i.checked){itemsDeleted++}
        }

        if(itemsDeleted > 0){
            Toast.makeText(this, "$itemsDeleted items deleted", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, "No items selected", Toast.LENGTH_LONG).show()
        }
        my_rv_Adapter.deleteItems()
        return super.onOptionsItemSelected(item)
    }
}