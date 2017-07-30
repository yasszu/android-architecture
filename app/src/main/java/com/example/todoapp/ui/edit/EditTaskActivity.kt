package com.example.todoapp.ui.edit

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.example.todoapp.R
import com.example.todoapp.databinding.ActivityEditTaskBinding
import com.example.todoapp.ui.base.BaseActivity

/**
 * Created by Yasuhiro Suzuki on 2017/07/30.
 */

class EditTaskActivity: BaseActivity() {

    lateinit var binding: ActivityEditTaskBinding

    lateinit var viewModel: EditTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_task)
        initToolBar()
        initViewModel()
        initFragment()
    }

    fun initToolBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: android.view.Menu): Boolean {
        menuInflater.inflate(R.menu.menu_edit_task, menu)
        return true
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem) = when(item.itemId) {
        android.R.id.home -> {
            finish()
            true
        }
        R.id.action_done -> true
        else -> super.onOptionsItemSelected(item)
    }

    fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(EditTaskViewModel::class.java)
    }

    fun initFragment() {
        supportFragmentManager.findFragmentByTag(EditTaskFragment.TAG)?:
                EditTaskFragment.newInstance().apply {
                    supportFragmentManager.beginTransaction()
                            .add(R.id.container, this, EditTaskFragment.TAG)
                            .commit()
                }
    }

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, EditTaskActivity::class.java)
            activity.startActivity(intent)
        }
    }

}
