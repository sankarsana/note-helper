package com.bhaktaprogram.calendarview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.abs

class CalendarView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : View(context, attributeSet) {

    private var days = emptyList<DayOfMonthUi>()
    private val paints = Paints(context)
    private var lastTouchX = 0f
    private var lastTouchY = 0f
    private val dimensions = Dimensions(
        resources.getDimensionPixelSize(R.dimen.day_of_month_default_padding).toFloat()
    )
    private val minTouchDistance = 2 * resources.displayMetrics.density + 0.5f
    private var selectIndex = -1
    private var onDaySelected: (DayOfMonthUi) -> Unit = {}

    init {
        if (isInEditMode) days = FakeDaysRepository.get()
    }

    fun setData(days: List<DayOfMonthUi>, selectIndex: Int) {
        if (days.size != COLUMNS * ROWS) {
            val msg = "The number of days should be ${COLUMNS * ROWS}. Actual is ${days.size}"
            throw IllegalArgumentException(msg)
        }
        this.days = days
        this.selectIndex = selectIndex
        invalidate()
    }

    fun setOnDaySelectListener(listener: (DayOfMonthUi) -> Unit) {
        onDaySelected = listener
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.UNSPECIFIED) {
            resources.getDimensionPixelSize(R.dimen.calendar_default_width)
        } else {
            MeasureSpec.getSize(widthMeasureSpec)
        }
        val height = if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.UNSPECIFIED) {
            resources.getDimensionPixelSize(R.dimen.calendar_default_height)
        } else {
            MeasureSpec.getSize(heightMeasureSpec)
        }
        setMeasuredDimension(width, height)
    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        dimensions.onViewSizeChanged(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        dimensions.reset()
        days.forEachIndexed { index, day ->
            if (day.eventType != null && day.currentMonth) drawEvent(canvas, day)
            if (day.isToday) drawToday(canvas)
            if (selectIndex == index) {
                drawSelection(canvas)
            }
            drawNumber(canvas, day)
            dimensions.moveToNext()
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
        canvas.drawArc(dimensions.dayRect, 90F, 180F, false, paint1)
        val paint2 = paints.getForEvent(EventType.Important)
        canvas.drawArc(dimensions.dayRect, -90F, 180F, false, paint2)
    }

    private fun drawCircleEvent(day: DayOfMonthUi, canvas: Canvas) {
        val paint = paints.getForEvent(day.eventType ?: return)
        canvas.drawOval(dimensions.dayRect, paint)
    }

    private fun drawToday(canvas: Canvas) = canvas.drawOval(dimensions.dayRect, paints.todayPaint)

    private fun drawSelection(canvas: Canvas) =
        canvas.drawOval(dimensions.dayRect, paints.selectionPaint)

    private fun drawNumber(canvas: Canvas, day: DayOfMonthUi) {
        val numberPaint = paints.getForText(day)
        val offsetY = (numberPaint.descent() + numberPaint.ascent()) / 2
        val y = dimensions.dayRect.centerY() - offsetY
        canvas.drawText(day.number, dimensions.dayRect.centerX(), y, numberPaint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                lastTouchX = event.x
                lastTouchY = event.y
            }
            MotionEvent.ACTION_UP -> {
                val isClick = abs(event.x - lastTouchX) <= minTouchDistance
                        && abs(event.y - lastTouchY) <= minTouchDistance
                if (isClick) performDayClick(event.x, event.y)
            }
        }
        return true
    }

    private fun performDayClick(x: Float, y: Float) {
        val index = dimensions.findCellIndex(x, y)
        val day = days[index]
        if (day.currentMonth) {
            selectIndex = index
            invalidate()
            onDaySelected(day)
        }
    }

    companion object {
        const val COLUMNS = 7
        const val ROWS = 6
    }
}