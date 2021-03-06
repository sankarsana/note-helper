package com.bhaktaprogram.main.custom.view

import android.content.Context
import android.graphics.Paint
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import com.bhaktaprogram.main.R

class Paints(private val context: Context) {

    private val numberMostImportant = getColor(R.color.number_most_important_event)
    private val numberDoubleImportant = getColor(R.color.number_double_event)
    private val numberImportant = getColor(R.color.number_important_event)
    private val numberSimple = getColor(R.color.number_simple_event)
    private val numberNothing = getColor(R.color.number_nothing_event)

    private val eventMostImportant = getColor(R.color.most_important_event)
    private val eventImportant = getColor(R.color.important_event)
    private val eventSimple = getColor(R.color.simple_event)

    private val numberPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textAlign = Paint.Align.CENTER
        textSize = getDimensionPixelSize(R.dimen.day_text_size).toFloat()
    }

    private val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

    val todayPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = getDimensionPixelSize(R.dimen.day_of_month_today_border).toFloat()
        color = getColor(R.color.day_of_month_today)
    }

    val selectionPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = getDimensionPixelSize(R.dimen.day_of_month_selected_border)
        color = getColor(R.color.day_of_month_focus)
    }

    fun getForEvent(eventType: EventType) = circlePaint.apply {
        color = getEventColor(eventType)
    }

    fun getForText(day: DayOfMonthUi) = numberPaint.apply {
        color = getTextColor(day)
    }

    private fun getTextColor(day: DayOfMonthUi): Int {
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

    private fun getDimensionPixelSize(@DimenRes id: Int) =
        context.resources.getDimensionPixelSize(id).toFloat()

    private fun getColor(@ColorRes id: Int) =
        context.resources.getColor(id, null)


    private fun getEventColor(eventType: EventType): Int = when (eventType) {
        EventType.MostImportant -> eventMostImportant
        EventType.Important -> eventImportant
        EventType.Simple -> eventSimple
        else -> throw IllegalArgumentException("No color for $eventType")
    }

}
