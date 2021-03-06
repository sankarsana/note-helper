package com.bhaktaprogram.main.custom.view

import android.content.Context
import androidx.annotation.ColorRes
import com.bhaktaprogram.main.R

class CalendarColors(private val context: Context) {

    private val numberMostImportant = getColor(R.color.number_most_important_event)
    private val numberDoubleImportant = getColor(R.color.number_double_event)
    private val numberImportant = getColor(R.color.number_important_event)
    private val numberSimple = getColor(R.color.number_simple_event)
    private val numberNothing = getColor(R.color.number_nothing_event)

    private val eventMostImportant = getColor(R.color.most_important_event)
    private val eventImportant = getColor(R.color.important_event)
    private val eventSimple = getColor(R.color.simple_event)

    fun getForText(day: DayOfMonthUi): Int {
        if (!day.currentMonth) {
            return getColor(R.color.number_another_month)
        }
        return when (day.eventType) {
            EventType.MostImportant -> numberMostImportant
            EventType.DoubleImportant -> numberDoubleImportant
            EventType.Important -> numberImportant
            EventType.Simple -> numberSimple
            EventType.Nothing -> numberNothing
        }
    }

    private fun getColor(@ColorRes id: Int) =
        context.resources.getColor(id, null)

    fun getForEvent(eventType: EventType): Int = when (eventType) {
        EventType.MostImportant -> eventMostImportant
        EventType.Important -> eventImportant
        EventType.Simple -> eventSimple
        else -> throw IllegalArgumentException("No color for $eventType")
    }

}
