package com.bhaktaprogram.calendarview

import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import androidx.core.util.forEach
import androidx.viewpager.widget.PagerAdapter
import com.bhaktaprogram.calendarview.month.MonthView
import java.util.*

class MonthAdapter : PagerAdapter() {

    private val minDate = Calendar.getInstance()
    private val maxDate = Calendar.getInstance()
    private var selectedDay: Calendar? = null
    private val items = SparseArray<ViewHolder>()

    private var count = 0
    private var firstDayOfWeek = Calendar.SUNDAY

    fun setRange(min: Calendar, max: Calendar) {
        minDate.timeInMillis = min.timeInMillis
        maxDate.timeInMillis = max.timeInMillis
        val diffYear = maxDate[Calendar.YEAR] - minDate[Calendar.YEAR]
        val diffMonth = maxDate[Calendar.MONTH] - minDate[Calendar.MONTH]
        count = diffMonth + MONTHS_IN_YEAR * diffYear + 1
        notifyDataSetChanged()
    }

    fun setSelectedDay(day: Calendar) {
        val oldPosition = getPositionForDay(selectedDay)
        val newPosition = getPositionForDay(day)

        // Clear the old position if necessary.
        if (oldPosition != newPosition && oldPosition >= 0) {
            items.get(oldPosition, null)?.monthView?.setSelectedDay(-1)
        }

        // Set the new position.
        if (newPosition >= 0) {
            val newMonthView = items.get(newPosition, null)
            if (newMonthView != null) {
                val dayOfMonth = day[Calendar.DAY_OF_MONTH]
                newMonthView.monthView.setSelectedDay(dayOfMonth)
            }
        }
        selectedDay = day
    }

    fun setFirstDayOfWeek(weekStart: Int) {
        firstDayOfWeek = weekStart
        items.forEach { _, value ->
            value.monthView.setFirstDayOfWeek(weekStart)
        }
    }

    private fun getMonthForPosition(position: Int): Int =
        (position + minDate.get(Calendar.MONTH)) % MONTHS_IN_YEAR

    private fun getYearForPosition(position: Int): Int {
        val yearOffset = (position + minDate.get(Calendar.MONTH)) / MONTHS_IN_YEAR
        return yearOffset + minDate.get(Calendar.YEAR)
    }

    private fun getPositionForDay(day: Calendar?): Int {
        if (day == null) return -1
        val yearOffset = day[Calendar.YEAR] - minDate[Calendar.YEAR]
        val monthOffset = day[Calendar.MONTH] - minDate[Calendar.MONTH]
        return yearOffset * MONTHS_IN_YEAR + monthOffset
    }

    override fun getCount() = count

    override fun isViewFromObject(view: View, any: Any): Boolean {
        val holder = any as? ViewHolder?
        return view == holder?.monthView
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val monthView = MonthView(container.context)
        val holder = ViewHolder(position, monthView)

        val month = getMonthForPosition(position)
        val year = getYearForPosition(position)

        val sDay = selectedDay
        val day = if (sDay != null && sDay.get(Calendar.MONTH) == month
            && sDay.get(Calendar.YEAR) == year
        ) {
            sDay.get(Calendar.DAY_OF_MONTH)
        } else -1

        monthView.setMonthParams(day, month, year, Calendar.MONDAY)

        container.addView(monthView)
        items.put(position, holder)
        return holder
    }

    override fun destroyItem(container: ViewGroup, position: Int, any: Any) {
        val holder = any as ViewHolder
        container.removeView(holder.monthView)
        items.remove(position)
    }

    private class ViewHolder(
        val position: Int,
        val monthView: MonthView
    )

    companion object {
        private const val MONTHS_IN_YEAR = 12
    }
}