package com.bhaktaprogram.main.custom.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import com.bhaktaprogram.main.R

class CalendarView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : View(context, attributeSet) {

    private var days = emptyList<DayOfMonthUi>()
    private val paints = Paints(context)
    private val dayRect = DayRect(
        resources.getDimensionPixelSize(R.dimen.day_of_month_default_padding).toFloat()
    )

    init {
        if (isInEditMode) days = FakeDaysRepository.get()
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
        dayRect.onViewSizeChanged(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        dayRect.reset()
        for (day in days) {
            if (day.currentMonth && day.eventType != EventType.Nothing) drawEvent(canvas, day)
            if (day.isToday) drawToday(canvas)
            if (day.isSelected) drawSelection(canvas)
            drawNumber(canvas, day)
            dayRect.moveToNext()
        }
    }

    private fun drawEvent(canvas: Canvas, day: DayOfMonthUi) {
        if (day.eventType == EventType.DoubleImportant) {
            drawDoubleEvent(canvas)
        } else {
            drawCircleEvent(day, canvas)
        }
    }

    private fun drawDoubleEvent(canvas: Canvas) {
        val paint1 = paints.getForEvent(EventType.MostImportant)
        val paint2 = paints.getForEvent(EventType.Important)
        canvas.drawArc(dayRect, 90F, 180F, false, paint1)
        canvas.drawArc(dayRect, -90F, 180F, false, paint2)
    }

    private fun drawCircleEvent(day: DayOfMonthUi, canvas: Canvas) {
        val paint = paints.getForEvent(day.eventType)
        canvas.drawOval(dayRect, paint)
    }

    private fun drawToday(canvas: Canvas) = canvas.drawOval(dayRect, paints.todayPaint)

    private fun drawSelection(canvas: Canvas) = canvas.drawOval(dayRect, paints.selectionPaint)

    private fun drawNumber(canvas: Canvas, day: DayOfMonthUi) {
        val numberPaint = paints.getForText(day)
        val offsetY = (numberPaint.descent() + numberPaint.ascent()) / 2
        val y = dayRect.centerY() - offsetY
        canvas.drawText(day.number, dayRect.centerX(), y, numberPaint)
    }

    companion object {
        const val COLUMNS = 7
        const val ROWS = 6
    }
}