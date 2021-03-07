package com.bhaktaprogram.calendarview

data class DayOfMonthUi(
    val number: String,
    val eventType: EventType? = null,
    val currentMonth: Boolean = true,
    val isToday: Boolean = false
)