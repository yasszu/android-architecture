package com.example.todoapp.ui.tasks

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.todoapp.databinding.ItemTaskBinding

/**
 * Created by Yasuhiro Suzuki on 2017/06/18.
 */
class TasksViewAdapter(val viewModel: TasksViewModel): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return viewModel.taskItems.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, position: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val binding = ItemTaskBinding.inflate(inflater, parent ,false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        holder as TaskViewHolder
        holder.bind(viewModel.taskItems[position])
    }

    class TaskViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: TaskViewModel) {
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }

    }

}