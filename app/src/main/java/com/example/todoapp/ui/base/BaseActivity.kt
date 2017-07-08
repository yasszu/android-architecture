package com.example.todoapp.ui.base

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View


/**
 * Created by Yasuhiro Suzuki on 2017/07/08.
 */
@SuppressLint("Registered")
open class BaseActivity : android.support.v7.app.AppCompatActivity() {

    val statusBarHeight: Int
        get() = if (resourceId > 0) resources.getDimensionPixelSize(resourceId) else 0

    val resourceId: Int
        get() = resources.getIdentifier("status_bar_height", "dimen", "android")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFullScreenMode()
    }

    fun setFullScreenMode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            findViewById(android.R.id.content).systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
    }

}