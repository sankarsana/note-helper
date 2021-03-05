package com.bhaktaprogram.main.custom.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.bhaktaprogram.main.R

class CalendarView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : View(context, attributeSet) {

    private var columnWidth = 0f
    private var columnHeight = 0f
    private val dayRect = RectF()
    private var days = emptyList<DayOfMonthUi>()
    private val numberTextSize = context.resources
        .getDimensionPixelSize(R.dimen.day_text_size).toFloat()

    private val numberCurrentMonthPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = context.resources.getColor(R.color.day_current_month, null)
        textAlign = Paint.Align.CENTER
        textSize = numberTextSize
        strokeWidth = 1f
    }

    private val numberAnotherMonthPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = context.resources.getColor(R.color.day_another_month, null)
        textAlign = Paint.Align.CENTER
        textSize = numberTextSize
        strokeWidth = 1f
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
        columnWidth = width.toFloat() / COLUMNS
        columnHeight = height.toFloat() / ROWS
    }

    override fun onDraw(canvas: Canvas) {
        dayRect.set(0f, 0f, columnWidth, columnHeight)
        for (day in days) {
//            if (day.number == "1" || day.number == "7" || day.number == "9")
//            canvas.drawRect(dayRect, numberCurrentMonthPaint)

            drawNumber(canvas, day)

            moveDayRect()
        }
    }

    private fun moveDayRect() {
        if (dayRect.right + columnWidth <= width) {
            dayRect.offset(columnWidth, 0f)
        } else {
            val newTop = dayRect.top + columnHeight
            val newBottom = newTop + columnHeight
            dayRect.set(0f, newTop, columnWidth, newBottom)
        }
    }

    private fun drawNumber(canvas: Canvas, day: DayOfMonthUi) {
        val paint = if (day.currentMonth) numberCurrentMonthPaint else numberAnotherMonthPaint
        val offsetY = (paint.descent() + paint.ascent()) / 2
        val y = dayRect.centerY() - offsetY
        canvas.drawText(day.number, dayRect.centerX(), y, paint)
    }

    companion object {
        private const val COLUMNS = 7
        private const val ROWS = 6
    }
}