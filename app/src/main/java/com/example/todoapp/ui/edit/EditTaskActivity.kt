package com.example.todoapp.ui.edit

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.Menu
import android.view.MenuItem
import com.example.todoapp.MyApplication
import com.example.todoapp.R
import com.example.todoapp.databinding.ActivityEditTaskBinding
import com.example.todoapp.ui.base.BaseActivity
import com.example.todoapp.ui.tasks.EditTaskViewModelFactory
import javax.inject.Inject

/**
 * Created by Yasuhiro Suzuki on 2017/07/30.
 */

class EditTaskActivity: BaseActivity() {

    @Inject
    lateinit var viewModelFactory: EditTaskViewModelFactory

    lateinit var binding: ActivityEditTaskBinding

    lateinit var viewModel: EditTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyApplication.appDatabaseComponent.inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_task)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(EditTaskViewModel::class.java)
        initToolBar()
        initFragment()
    }

    fun initToolBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_edit_task, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {
        android.R.id.home -> {
            finish()
            true
        }
        R.id.action_done -> save()
        else -> super.onOptionsItemSelected(item)
    }

    fun initFragment() {
        val tag = EditTaskFragment.TAG
        val fm = supportFragmentManager
        fm.findFragmentByTag(tag)?: EditTaskFragment.newInstance().apply {
            fm.beginTransaction().add(R.id.container, this, tag).commit()
        }
    }

    fun save(): Boolean {
        viewModel.save({ finishEdit() }, { showErrorSnackbar(it) })
        return true
    }

    fun showErrorSnackbar(message: String) {
        val snackbar = Snackbar.make(binding.parentLayout, message, Snackbar.LENGTH_LONG)
        snackbar.setAction("OK", { snackbar.dismiss() })
        snackbar.show()
    }

    fun finishEdit() {
        setResult(Activity.RESULT_OK)
        finish()
    }

    companion object {
        fun start(activity: Activity, requestCode: Int) {
            val intent = Intent(activity, EditTaskActivity::class.java)
            activity.startActivityForResult(intent, requestCode)
        }
    }

}
