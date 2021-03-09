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
        private set
    var paddingLeft = 0f
        private set
    var paddingTop = 0f
        private set

    private var paddedWidth = 0f
    private var paddedHeight = 0f

    fun onViewSizeChanged(
        width: Int,
        height: Int,
        paddingLeft: Int,
        paddingTop: Int,
        paddingRight: Int,
        paddingBottom: Int
    ) {
        val cellMinPadding = getDimensionPixelSize(R.dimen.day_of_month_min_padding)
        val maxDiameter = getDimensionPixelSize(R.dimen.day_of_month_selected_diameter)
        dayOfWeekHeight = getDimensionPixelSize(R.dimen.days_of_week_height)
        this.paddingLeft = paddingLeft.toFloat()
        this.paddingTop = paddingTop.toFloat()
        paddedWidth = (width - paddingLeft - paddingRight).toFloat()
        paddedHeight = (height - paddingTop - paddingBottom).toFloat()
        cellWidth = paddedWidth / CalendarView.COLUMNS
        cellHeight = (paddedHeight - dayOfWeekHeight) / CalendarView.ROWS
        val diameterByCell = min(cellWidth, cellHeight) - cellMinPadding * 2
        val realDiameter = if (diameterByCell > maxDiameter) maxDiameter else diameterByCell
        selectionRadius = realDiameter / 2
    }

    private fun getDimensionPixelSize(@DimenRes resId: Int) =
        resources.getDimensionPixelSize(resId).toFloat()

    fun findCellIndex(x: Float, y: Float): Int {
        val paddedX = x - paddingLeft
        if (paddedX < 0 || paddedX >= paddedWidth) {
            return -1
        }

        val paddedY = y - dayOfWeekHeight - paddingTop
        if (paddedY < 0 || paddedY >= paddedHeight) {
            return -1
        }

        val coll = (paddedX / cellWidth).toInt()
        val row = (paddedY / cellHeight).toInt()
        return row * CalendarView.COLUMNS + coll
    }
}