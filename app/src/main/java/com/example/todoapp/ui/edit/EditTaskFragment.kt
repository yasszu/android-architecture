package com.example.todoapp.ui.edit

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoapp.databinding.FragmentEditTaskBinding
import com.example.todoapp.ui.base.BaseFragment

/**
 * Created by Yasuhiro Suzuki on 2017/07/30.
 */
class EditTaskFragment: BaseFragment() {

    lateinit var binding: FragmentEditTaskBinding

    val viewModel: EditTaskViewModel by lazy {
        ViewModelProviders.of(activity).get(EditTaskViewModel::class.java)
    }

    companion object {
        val TAG: String = EditTaskFragment::class.java.simpleName
        fun newInstance() = EditTaskFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentEditTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

}