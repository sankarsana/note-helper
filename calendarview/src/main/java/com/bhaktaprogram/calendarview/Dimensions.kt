package com.bhaktaprogram.calendarview

import android.graphics.RectF
import kotlin.math.min

class Dimensions(private val dayCellPadding: Float) {
    val dayRect = RectF()
    private var cellWidth = 0f
    private var cellHeight = 0f
    private var cellHorizontalPadding = 0f
    private var cellVerticalPadding = 0f
    private var circleDiameter = 0f
    private var viewWidth = 0

    fun onViewSizeChanged(width: Int, height: Int) {
        viewWidth = width
        cellWidth = width.toFloat() / CalendarView.COLUMNS
        cellHeight = height.toFloat() / CalendarView.ROWS
        circleDiameter = min(cellWidth, cellHeight) - dayCellPadding * 2
        cellHorizontalPadding = (cellWidth - circleDiameter) / 2
        cellVerticalPadding = (cellHeight - circleDiameter) / 2
    }

    fun reset() {
        dayRect.set(
            cellHorizontalPadding,
            cellVerticalPadding,
            cellHorizontalPadding + circleDiameter,
            cellVerticalPadding + circleDiameter
        )
    }

    fun moveToNext() {
        if (dayRect.right + cellWidth <= viewWidth) {
            dayRect.offset(cellWidth, 0f)
        } else {
            dayRect.offsetTo(cellHorizontalPadding, dayRect.top + cellHeight)
        }
    }

    fun findCellIndex(x: Float, y: Float): Int {
        val coll = (x / cellWidth).toInt()
        val row = (y / cellHeight).toInt()
        return row * CalendarView.COLUMNS + coll
    }
}