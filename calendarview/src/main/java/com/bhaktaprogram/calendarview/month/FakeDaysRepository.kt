package com.bhaktaprogram.calendarview.month

object FakeDaysRepository {

    fun getEvents() = hashMapOf(
        9 to EventType.MostImportant,
        25 to EventType.MostImportant,
        17 to EventType.DoubleImportant,
        3 to EventType.Important,
        28 to EventType.Important,
        10 to EventType.Simple,
        12 to EventType.Simple,
        14 to EventType.Simple,
        26 to EventType.Simple,
        29 to EventType.Simple,
    )
}