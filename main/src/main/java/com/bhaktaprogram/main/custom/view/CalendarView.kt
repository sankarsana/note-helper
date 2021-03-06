package com.bhaktaprogram.main.custom.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.bhaktaprogram.main.R
import kotlin.math.min

class CalendarView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : View(context, attributeSet) {

    private var cellWidth = 0f
    private var cellHeight = 0f
    private var horizontalPadding = 0f
    private var verticalPadding = 0f
    private var dayViewSize = 0f
    private val dayRect = RectF()
    private var days = emptyList<DayOfMonthUi>()
    private val colors = CalendarColors(context)

    private val numberPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textAlign = Paint.Align.CENTER
        textSize = resources.getDimensionPixelSize(R.dimen.day_text_size).toFloat()
    }

    private val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

    private val todayPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = resources.getDimensionPixelSize(R.dimen.day_of_month_today_border).toFloat()
        color = resources.getColor(R.color.day_of_month_today, null)
    }

    private val selectionPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth =
            resources.getDimensionPixelSize(R.dimen.day_of_month_selected_border).toFloat()
        color = resources.getColor(R.color.day_of_month_focus, null)
    }

    init {
        if (isInEditMode) {
            days = FakeDaysRepository.get()
        }
    }

    fun setData(days: List<DayOfMonthUi>) {
        if (days.size != COLUMNS * ROWS) {
            val msg = "The number of days should be ${COLUMNS * ROWS}. Actual is ${days.size}"
            throw IllegalArgumentException(msg)
        }
        this.days = days
        invalidate()
    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        Log.i("TAG", "onSizeChanged width: $width, height: $height")
        cellWidth = width.toFloat() / COLUMNS
        cellHeight = height.toFloat() / ROWS
        val cellPadding = resources
            .getDimensionPixelSize(R.dimen.day_of_month_default_padding).toFloat()
        dayViewSize = min(cellWidth, cellHeight) - cellPadding * 2
        horizontalPadding = (cellWidth - dayViewSize) / 2
        verticalPadding = (cellHeight - dayViewSize) / 2
    }

    override fun onDraw(canvas: Canvas) {
        resetRect()
        for (day in days) {
            if (day.currentMonth && day.eventType != EventType.Nothing) drawEvent(canvas, day)
            if (day.isToday) drawToday(canvas)
            if (day.isSelected) drawSelection(canvas)
            drawNumber(canvas, day)

            moveDayRect()
        }
    }

    private fun resetRect() {
        dayRect.set(
            horizontalPadding,
            verticalPadding,
            horizontalPadding + dayViewSize,
            verticalPadding + dayViewSize
        )
    }

    private fun drawEvent(canvas: Canvas, day: DayOfMonthUi) {
        if (day.eventType == EventType.DoubleImportant) {

        } else {
            circlePaint.color = colors.getForEvent(day.eventType)
            canvas.drawOval(dayRect, circlePaint)
        }
    }

    private fun moveDayRect() {
        if (dayRect.right + cellWidth <= width) {
            dayRect.offset(cellWidth, 0f)
        } else {
            dayRect.offsetTo(horizontalPadding, dayRect.top + cellHeight)
        }
    }

    private fun drawToday(canvas: Canvas) {
        canvas.drawOval(dayRect, todayPaint)
    }

    private fun drawSelection(canvas: Canvas) {
        canvas.drawOval(dayRect, selectionPaint)
    }

    private fun drawNumber(canvas: Canvas, day: DayOfMonthUi) {
        numberPaint.color = colors.getForText(day)
        val offsetY = (numberPaint.descent() + numberPaint.ascent()) / 2
        val y = dayRect.centerY() - offsetY
        canvas.drawText(day.number, dayRect.centerX(), y, numberPaint)
    }

    companion object {
        private const val COLUMNS = 7
        private const val ROWS = 6
    }
}