package com.bhaktaprogram.calendarview.month

import android.content.Context
import android.graphics.Paint
import android.text.TextPaint
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import com.bhaktaprogram.calendarview.R

class Paints(private val context: Context) {

    private val numberMostImportant = getColor(R.color.number_most_important_event)
    private val numberDoubleImportant = getColor(R.color.number_double_event)
    private val numberImportant = getColor(R.color.number_important_event)
    private val numberSimple = getColor(R.color.number_simple_event)
    private val numberNothing = getColor(R.color.number_nothing_event)

    private val eventMostImportant = getColor(R.color.most_important_event)
    private val eventImportant = getColor(R.color.important_event)
    private val eventSimple = getColor(R.color.simple_event)

    val dayPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textAlign = Paint.Align.CENTER
        textSize = getDimensionPixelSize(R.dimen.day_text_size)
    }

    val dayOfWeekPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textAlign = Paint.Align.CENTER
        textSize = getDimensionPixelSize(R.dimen.days_of_week_text)
    }

    val eventPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

    val todayPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = getDimensionPixelSize(R.dimen.day_of_month_today_border)
        color = getColor(R.color.day_of_month_today)
    }

    val selectionPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = getDimensionPixelSize(R.dimen.day_of_month_selected_border)
        color = getColor(R.color.day_of_month_focus)
    }

    fun getDayTextColor(event: EventType?) = when (event) {
        EventType.MostImportant -> numberMostImportant
        EventType.DoubleImportant -> numberDoubleImportant
        EventType.Important -> numberImportant
        EventType.Simple -> numberSimple
        else -> numberNothing
    }

    fun getEventColor(eventType: EventType): Int = when (eventType) {
        EventType.MostImportant -> eventMostImportant
        EventType.Important -> eventImportant
        EventType.Simple -> eventSimple
        else -> throw IllegalArgumentException("No color for $eventType")
    }

    private fun getDimensionPixelSize(@DimenRes id: Int) =
        context.resources.getDimensionPixelSize(id).toFloat()

    private fun getColor(@ColorRes id: Int) =
        context.resources.getColor(id, null)

}
