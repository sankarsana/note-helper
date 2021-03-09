package com.bhaktaprogram.calendarview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import java.util.*
import kotlin.math.abs

class CalendarView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : View(context, attributeSet) {
    private val paints = Paints(context)
    private val dim = Dimensions(resources)
    private val rect = RectF()
    private var lastTouchX = 0f
    private var lastTouchY = 0f
    private var onDaySelected: (Calendar) -> Unit = {}

    private val calendar = Calendar.getInstance(/*resources.configuration.locale*/)
    private var weekStart = Calendar.SUNDAY
    private var dayOfWeekStart = 0
    private var daysInMonth = 0
    private var selectedDay = -1
    private var today = -1

    private var events = hashMapOf<Int, EventType>()
    private val dayOfWeekLabels = Array(7) { "" }

    init {
        if (isInEditMode) {
            setMonthParams(4, Calendar.MARCH, 2021, Calendar.MONDAY)
            setEvents(FakeDaysRepository.getEvents())
        }
        updateDayOfWeekLabels()
    }

    fun setMonthParams(selectedDay: Int, month: Int, year: Int, weekStart: Int) {
        this.selectedDay = selectedDay
        this.weekStart = weekStart
        calendar.set(year, month, 1)
        dayOfWeekStart = calendar[Calendar.DAY_OF_WEEK]
        daysInMonth = getDaysInMonth(month, year)

        updateToday(year, month)
        updateDayOfWeekLabels()

        invalidate()
    }

    fun setEvents(events: HashMap<Int, EventType>) {
        this.events = events
        invalidate()
    }

    private fun updateToday(year: Int, month: Int) {
        val todayCalendar = Calendar.getInstance()
        today = if (
            todayCalendar[Calendar.YEAR] == year
            && todayCalendar[Calendar.MONTH] == month
        ) todayCalendar[Calendar.DAY_OF_MONTH] else -1
    }

    private fun updateDayOfWeekLabels() {
        val labels = resources.getStringArray(R.array.day_offweek_labels)
        for (i in 0 until COLUMNS) {
            dayOfWeekLabels[i] = labels[(i + weekStart - 1) % COLUMNS]
        }
    }

    fun setOnDaySelectListener(listener: (Calendar) -> Unit) {
        onDaySelected = listener
    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        dim.onViewSizeChanged(width, height, paddingLeft, paddingTop, paddingRight, paddingBottom)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.translate(dim.paddingLeft, dim.paddingTop)

        drawDaysOfWeek(canvas)
        drawDays(canvas)

        canvas.translate(-dim.paddingLeft, -dim.paddingTop)
    }

    private fun drawDaysOfWeek(canvas: Canvas) {
        val paint = paints.dayOfWeekPaint
        val halfLineHeight = (paint.ascent() + paint.descent()) / 2
        val y = dim.dayOfWeekHeight / 2 - halfLineHeight
        for (col in 0 until COLUMNS) {
            val x = dim.cellWidth * col + dim.cellWidth / 2
            val label = dayOfWeekLabels[col]
            canvas.drawText(label, x, y, paint)
        }
    }

    private fun drawDays(canvas: Canvas) {
        val dayPaint = paints.dayPaint
        val eventPaint = paints.eventPaint
        val halfLineHeight: Float = (dayPaint.ascent() + dayPaint.descent()) / 2f
        var rowCenter = dim.dayOfWeekHeight + dim.cellHeight / 2
        var col = findDayOffset()
        val radius = dim.selectionRadius

        for (day in 1..daysInMonth) {
            val colCenter = dim.cellWidth * col + dim.cellWidth / 2
            val event = events[day]

            if (event == EventType.DoubleImportant) {
                rect.set(
                    colCenter - radius,
                    rowCenter - radius,
                    colCenter + radius,
                    rowCenter + radius
                )
                eventPaint.color = paints.getEventColor(EventType.MostImportant)
                canvas.drawArc(rect, 90F, 180F, false, eventPaint)
                eventPaint.color = paints.getEventColor(EventType.Important)
                canvas.drawArc(rect, -90F, 180F, false, eventPaint)
            } else if (event != null) {
                eventPaint.color = paints.getEventColor(event)
                canvas.drawCircle(colCenter, rowCenter, dim.selectionRadius, eventPaint)
            }

            if (today == day) {
                canvas.drawCircle(colCenter, rowCenter, dim.selectionRadius, paints.todayPaint)
            }
            if (day == selectedDay) {
                canvas.drawCircle(colCenter, rowCenter, dim.selectionRadius, paints.selectionPaint)
            }

            dayPaint.color = paints.getDayTextColor(event)
            canvas.drawText(day.toString(), colCenter, rowCenter - halfLineHeight, dayPaint)

            col++
            if (col == COLUMNS) {
                col = 0
                rowCenter += dim.cellHeight
            }
        }
    }

    private fun findDayOffset(): Int {
        val offset: Int = dayOfWeekStart - weekStart
        return if (dayOfWeekStart < weekStart) offset + ROWS else offset
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                lastTouchX = event.x
                lastTouchY = event.y
            }
            MotionEvent.ACTION_UP -> {
                val minTouchDistance = 2 * resources.displayMetrics.density + 0.5f
                val isClick = abs(event.x - lastTouchX) <= minTouchDistance
                        && abs(event.y - lastTouchY) <= minTouchDistance
                if (isClick) performDayClick(event.x, event.y)
            }
        }
        return true
    }

    private fun performDayClick(x: Float, y: Float) {
        val index = dim.findCellIndex(x, y)
        val day = index + 1 + findDayOffset()
        if (isValidDayOfMonth(day)) {
            selectedDay = day
            invalidate()
            val date = Calendar.getInstance()
            date.set(calendar[Calendar.YEAR], calendar[Calendar.MONTH], day)
            onDaySelected(date)
        }
    }

    private fun isValidDayOfMonth(day: Int) = day in 1..daysInMonth

    companion object {
        const val COLUMNS = 7
        const val ROWS = 6

        private fun getDaysInMonth(month: Int, year: Int): Int {
            return when (month) {
                Calendar.JANUARY, Calendar.MARCH, Calendar.MAY,
                Calendar.JULY, Calendar.AUGUST, Calendar.OCTOBER, Calendar.DECEMBER -> 31
                Calendar.APRIL, Calendar.JUNE, Calendar.SEPTEMBER, Calendar.NOVEMBER -> 30
                Calendar.FEBRUARY -> if (year % 4 == 0) 29 else 28
                else -> throw java.lang.IllegalArgumentException("Invalid Month")
            }
        }
    }
}