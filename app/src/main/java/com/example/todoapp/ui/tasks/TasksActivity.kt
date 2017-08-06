package com.example.todoapp.ui.tasks

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.todoapp.R
import com.example.todoapp.databinding.ActivityTasksBinding
import com.example.todoapp.ui.base.BaseActivity
import com.example.todoapp.ui.edit.EditTaskActivity

class TasksActivity: BaseActivity(), TasksViewModel.Listener {

    val REQUEST_EDIT_TASK = 1

    lateinit var binding: ActivityTasksBinding

    lateinit var viewModel: TasksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tasks)
        setSupportActionBar(binding.toolbar)
        initViewModel()
        initFragment()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_EDIT_TASK -> { Log.d("EditTask", "RESULT_OK") }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {
        R.id.action_settings -> true
        else -> super.onOptionsItemSelected(item)
    }

    override fun onRemoveItem() {
        showDeleteSnackbar()
    }

    override fun onClickFAB() {
        EditTaskActivity.start(this, REQUEST_EDIT_TASK)
    }

    fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(TasksViewModel::class.java)
        viewModel.listener = this
        binding.viewModel = viewModel
    }

    fun initFragment() {
        val fm = supportFragmentManager
        val tag = TasksFragment.TAG
        fm.findFragmentByTag(tag)?: TasksFragment.newInstance().apply {
            fm.beginTransaction()
                    .add(R.id.container, this, tag)
                    .commit()
        }
    }

    fun showDeleteSnackbar() {
        Snackbar.make(binding.parentLayout, R.string.item_remove, Snackbar.LENGTH_LONG)
                .setAction(R.string.undo, { viewModel.restoreLastItems() })
                .show()
    }

}
