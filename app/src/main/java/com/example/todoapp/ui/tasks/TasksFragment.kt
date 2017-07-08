package com.example.todoapp.ui.tasks

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoapp.databinding.FragmentTasksBinding
import com.example.todoapp.ui.base.BaseFragment

/**
 * A placeholder fragment containing a simple view.
 */
class TasksFragment : BaseFragment(){

    lateinit var binding: FragmentTasksBinding

    val viewModel: TasksViewModel by lazy {
        ViewModelProviders.of(activity).get(TasksViewModel::class.java)
    }

    companion object {
        val TAG: String = TasksFragment::class.java.simpleName
        fun newInstance() = TasksFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTasksBinding.inflate(inflater, container, false)
        initRecyclerView()
        return binding.root
    }

    fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = TasksViewAdapter(viewModel)
        binding.recyclerView.layoutManager = layoutManager
    }

}

