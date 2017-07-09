package com.example.todoapp.helper

import android.graphics.Canvas
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper


/**
 * Created by Yasuhiro Suzuki on 2017/07/09.
 */
class ItemTouchHelperCallback(val adapter: ItemTouchHelperAdapter): ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN,
        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

    val ALPHA_FULL = 1.0f
    val ALPHA_MAGNIFICATION = 1.2f

    override fun onMove(recyclerView: RecyclerView, holder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        val fromPos = holder.adapterPosition
        val toPos = target.adapterPosition
        adapter.onMoveItem(fromPos, toPos)
        return true
    }

    override fun onSwiped(holder: RecyclerView.ViewHolder, direction: Int) {
        val fromPos = holder.adapterPosition
        adapter.onRemoveItem(fromPos)
    }

    override fun onChildDraw(c: Canvas?, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            val alpha = ALPHA_FULL - (Math.abs(dX) / viewHolder.itemView.width.toFloat()) * ALPHA_MAGNIFICATION
            viewHolder.itemView.alpha = alpha
            viewHolder.itemView.translationX = dX
        } else {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        }
    }

}