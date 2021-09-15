package com.learning.todolist.ui.todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.learning.todolist.data.model.TodoModel
import com.learning.todolist.databinding.ItemListBinding

class MainAdapter(private val list: ArrayList<TodoModel>): RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(val itemBinding : ItemListBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: TodoModel) {
            itemBinding.textViewName.text = item.title
            itemBinding.textViewStatus.text = item.completed.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemBinding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val item = list.get(position)
       // holder.itemBinding.
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addList(items: List<TodoModel>) {
        this.list.apply {
            clear()
            addAll(items)
        }
    }
}