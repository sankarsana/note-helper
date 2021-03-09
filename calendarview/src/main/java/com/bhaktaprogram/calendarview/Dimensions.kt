package com.bhaktaprogram.calendarview

import android.content.res.Resources
import androidx.annotation.DimenRes
import kotlin.math.min

class Dimensions(private val resources: Resources) {
    var cellWidth = 0f
        private set
    var cellHeight = 0f
        private set
    var selectionRadius = 0f
        private set
    var dayOfWeekHeight = 0f

    fun onViewSizeChanged(width: Int, height: Int) {
        val cellMinPadding = getDimensionPixelSize(R.dimen.day_of_month_min_padding)
        val maxDiameter = getDimensionPixelSize(R.dimen.day_of_month_selected_diameter)
        dayOfWeekHeight = getDimensionPixelSize(R.dimen.days_of_week_height)
        cellWidth = width.toFloat() / CalendarView.COLUMNS
        cellHeight = (height.toFloat() - dayOfWeekHeight) / CalendarView.ROWS
        val diameterByCell = min(cellWidth, cellHeight) - cellMinPadding * 2
        val realDiameter = if (diameterByCell > maxDiameter) maxDiameter else diameterByCell
        selectionRadius = realDiameter / 2
    }

    private fun getDimensionPixelSize(@DimenRes resId: Int) =
        resources.getDimensionPixelSize(resId).toFloat()

    fun findCellIndex(x: Float, y: Float): Int {
        val coll = (x / cellWidth).toInt()
        val row = (y / cellHeight).toInt()
        return row * CalendarView.COLUMNS + coll
    }
}