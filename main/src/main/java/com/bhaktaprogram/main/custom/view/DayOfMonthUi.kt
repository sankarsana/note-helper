package com.bhaktaprogram.main.custom.view

data class DayOfMonthUi(
    val number: String,
    val eventType: EventType = EventType.Nothing,
    val currentMonth: Boolean = true,
    val isToday: Boolean = false
)