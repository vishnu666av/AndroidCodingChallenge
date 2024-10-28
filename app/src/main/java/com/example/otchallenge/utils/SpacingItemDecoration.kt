package com.example.otchallenge.utils

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Custom ItemDecoration for RecyclerView that adds spacing between items
 * and draws a colored separator beneath each item.
 *
 * This class allows for customizable spacing, separator height, and
 * separator color, making it easy to enhance the visual layout of a
 * RecyclerView. The `getItemOffsets` method calculates the spacing
 * around each item based on its position, while the `onDrawOver`
 * method handles drawing the separators on the canvas.
 *
 * This implementation supports grid layouts by considering the number
 * of columns and ensures proper spacing on edges, improving the
 * overall appearance of lists displayed in the RecyclerView.
 */
class SpacingItemDecoration(
    private val spacing: Int,
    private val separatorHeight: Int,
    private val separatorColor: Int
) : RecyclerView.ItemDecoration() {

    private val paint = Paint().apply {
        color = separatorColor
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        val columnCount = (parent.layoutManager as? GridLayoutManager)?.spanCount ?: 1
        val left = if (position % columnCount == 0) spacing else spacing / 2
        val right = if (position % columnCount == columnCount - 1) spacing else spacing / 2
        val top = if (position < columnCount) spacing else spacing
        val bottom = spacing
        outRect.set(left, top, right, bottom)
    }

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i) ?: continue
            val params = child.layoutParams as RecyclerView.LayoutParams
            val left = child.left
            val right = child.right
            val top = child.bottom + params.bottomMargin
            val bottom = top + separatorHeight
            canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint)
        }
    }
}
