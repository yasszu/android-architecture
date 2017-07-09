package com.example.todoapp.helper

/**
 * Created by Yasuhiro Suzuki on 2017/07/09.
 */
interface ItemTouchHelperAdapter {
    fun onMoveItem(from: Int, to: Int)
    fun onRemoveItem(from: Int)
}