package com.example.todoapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class RecyclerViewAdapter (private val todo_items: ArrayList<ToDoItem>):
    RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {
    class ItemViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val todo = todo_items[position]

        holder.itemView.apply {
            todo_textView.text = todo.text
            todo_checkbox.isChecked = todo.checked
            todo_checkbox.setOnCheckedChangeListener { _, isChecked ->
                if(isChecked){
                    todo_checkbox.setTextColor(Color.GRAY)
                }else{
                    todo_checkbox.setTextColor(Color.BLACK)
                }
                todo.checked = !todo.checked
            }
        }
    }

    override fun getItemCount(): Int = todo_items.size


    fun deleteItems(){
        todo_items.removeAll{ todo -> todo.checked }
        notifyDataSetChanged()
    }
}