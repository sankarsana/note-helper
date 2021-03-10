package com.bhaktaprogram.calendarview

import android.content.Context
import android.util.AttributeSet
import androidx.viewpager.widget.ViewPager
import java.util.*

class CalendarViewPager @JvmOverloads constructor(
    context: Context, attributeSet: AttributeSet? = null
) : ViewPager(context, attributeSet) {

    private val selectedDay = Calendar.getInstance()
    private val minDate = Calendar.getInstance()
    private val maxDate = Calendar.getInstance()
    private val tempCalendar by lazy { Calendar.getInstance() }

    private val adapter = MonthAdapter()

    init {
        setAdapter(adapter)
        setFirstDayOfWeek(Calendar.MONDAY)
        minDate.set(1900, Calendar.JANUARY, 1)
        maxDate.set(2100, Calendar.DECEMBER, 31)
        onRangeChanged()
        setDate(System.currentTimeMillis(), false)
    }

    private fun setFirstDayOfWeek(firstDayOfWeek: Int) {
        adapter.setFirstDayOfWeek(firstDayOfWeek)
    }

    private fun onRangeChanged() {
        adapter.setRange(minDate, maxDate)

        // Changing the min/max date changes the selection position since we
        // don't really have stable IDs. Jumps immediately to the new position.
        setDate(selectedDay.timeInMillis, animate = false, setSelected = false)
    }

    private fun setDate(timeInMillis: Long, animate: Boolean, setSelected: Boolean = true) {
        var millis = timeInMillis
        var dateClamped = false
        // Clamp the target day in milliseconds to the min or max if outside the range.
        if (millis < minDate.timeInMillis) {
            millis = minDate.timeInMillis
            dateClamped = true
        } else if (millis > maxDate.timeInMillis) {
            millis = maxDate.timeInMillis
            dateClamped = true
        }
        if (setSelected || dateClamped) {
            selectedDay.timeInMillis = millis
        }
        val position = getPositionFromDay(millis)
        if (position != currentItem) {
            setCurrentItem(position, animate)
        }
        tempCalendar.timeInMillis = millis
        adapter.setSelectedDay(tempCalendar)
    }

    private fun getPositionFromDay(millis: Long): Int {
        val diffMonthMax = getDiffMonths(minDate, maxDate)
        tempCalendar.timeInMillis = millis
        val diffMonth = getDiffMonths(minDate, tempCalendar)
        return constrain(diffMonth, 0, diffMonthMax)
    }

    private fun getDiffMonths(start: Calendar, end: Calendar): Int {
        val diffYears = end[Calendar.YEAR] - start[Calendar.YEAR]
        return end[Calendar.MONTH] - start[Calendar.MONTH] + 12 * diffYears
    }

    companion object {
        fun constrain(amount: Int, low: Int, high: Int) =
            if (amount < low) low else if (amount > high) high else amount
    }
}