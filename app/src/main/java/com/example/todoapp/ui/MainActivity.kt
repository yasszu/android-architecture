package com.example.todoapp.ui

import com.example.todoapp.R

class MainActivity : android.support.v7.app.AppCompatActivity() {

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.todoapp.R.layout.activity_main)
        initToolbar()
        initFragment()
    }

    override fun onCreateOptionsMenu(menu: android.view.Menu): Boolean {
        menuInflater.inflate(com.example.todoapp.R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem) = when(item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
    }

    fun initToolbar() {
        val toolbar = findViewById(com.example.todoapp.R.id.toolbar) as android.support.v7.widget.Toolbar
        setSupportActionBar(toolbar)
    }

    fun initFragment() {
    }

}
