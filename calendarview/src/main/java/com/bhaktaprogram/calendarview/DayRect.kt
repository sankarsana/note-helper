package com.bhaktaprogram.calendarview

import android.graphics.RectF
import kotlin.math.min

class DayRect(private val padding: Float) : RectF() {
    private var cellWidth = 0f
    private var cellHeight = 0f
    private var horizontalPadding = 0f
    private var verticalPadding = 0f
    private var dayViewSize = 0f
    private var viewWidth = 0

    fun onViewSizeChanged(width: Int, height: Int) {
        viewWidth = width
        cellWidth = width.toFloat() / CalendarView.COLUMNS
        cellHeight = height.toFloat() / CalendarView.ROWS
        dayViewSize = min(cellWidth, cellHeight) - padding * 2
        horizontalPadding = (cellWidth - dayViewSize) / 2
        verticalPadding = (cellHeight - dayViewSize) / 2
    }

    fun reset() {
        set(
            horizontalPadding,
            verticalPadding,
            horizontalPadding + dayViewSize,
            verticalPadding + dayViewSize
        )
    }

    fun moveToNext() {
        if (right + cellWidth <= viewWidth) {
            offset(cellWidth, 0f)
        } else {
            offsetTo(horizontalPadding, top + cellHeight)
        }
    }

    fun findCellIndex(x: Float, y: Float): Int {
        val coll = (x / cellWidth).toInt()
        val row = (y / cellHeight).toInt()
        return row * CalendarView.COLUMNS + coll
    }
}