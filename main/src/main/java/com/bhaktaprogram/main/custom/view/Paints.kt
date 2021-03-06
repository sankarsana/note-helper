package com.bhaktaprogram.main.custom.view

import android.content.Context
import android.graphics.Paint
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import com.bhaktaprogram.main.R

class Paints(private val context: Context) {

    private val numberTextSize = context.resources
        .getDimensionPixelSize(R.dimen.day_text_size).toFloat()
    val todayBorderWidth = getDimensionPixelSize(R.dimen.day_of_month_today_border)
    val selectBorderWidth = getDimensionPixelSize(R.dimen.day_of_month_selected_border)

    val numberAnotherMonth = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = context.resources.getColor(R.color.number_another_month, null)
        textAlign = Paint.Align.CENTER
        textSize = numberTextSize
    }

    val today = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = todayBorderWidth.toFloat()
        color = getColor(R.color.day_of_month_today)
    }

    val focus = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = selectBorderWidth.toFloat()
        color = getColor(R.color.day_of_month_focus)
    }

    val mostImportantEvent = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = getColor(R.color.most_important_event)
        style = Paint.Style.FILL
    }

    val importantEvent = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = getColor(R.color.important_event)
        style = Paint.Style.FILL
    }

    val simpleEvent = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = getColor(R.color.simple_event)
        style = Paint.Style.FILL
    }

    private fun getDimensionPixelSize(@DimenRes id: Int) =
        context.resources.getDimensionPixelSize(id)

    private fun getColor(@ColorRes id: Int) =
        context.resources.getColor(id, null)
}