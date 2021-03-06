package com.bhaktaprogram.main.custom.view

import android.content.Context
import androidx.annotation.ColorRes
import com.bhaktaprogram.main.R

class CalendarColors(private val context: Context) {

    fun getForText(day: DayOfMonthUi): Int {
        if (!day.currentMonth) {
            return getColor(R.color.number_another_month)
        }
        return when (day.eventType) {
            EventType.MostImportant -> getColor(R.color.number_most_important_event)
            EventType.DoubleImportant -> getColor(R.color.number_double_event)
            EventType.Important -> getColor(R.color.number_important_event)
            EventType.Simple -> getColor(R.color.number_simple_event)
            EventType.Nothing -> getColor(R.color.number_nothing_event)
        }
    }

    private fun getColor(@ColorRes id: Int) =
        context.resources.getColor(id, null)

}
