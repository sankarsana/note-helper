package com.bhaktaprogram.calendarview

object FakeDaysRepository {

    fun get(): List<DayOfMonthUi> {
        val days = mutableListOf<DayOfMonthUi>()
        createAfterCurrentMonth(days, 2)
        createCurrentMonth(days, 31)
        createAfterCurrentMonth(days, 9)
        return days
    }

    private fun createCurrentMonth(days: MutableList<DayOfMonthUi>, count: Int) {
        repeat(count) {
            val number = it + 1
            val day = DayOfMonthUi(number.toString())
            days.add(day)
        }
        days[11] = days[11].copy(eventType = EventType.MostImportant)
        days[27] = days[27].copy(eventType = EventType.MostImportant)

        days[16] = days[16].copy(eventType = EventType.DoubleImportant)

        days[5] = days[5].copy(eventType = EventType.Important)
        days[30] = days[30].copy(eventType = EventType.Important)

        days[12] = days[12].copy(eventType = EventType.Simple)
        days[14] = days[14].copy(eventType = EventType.Simple)
        days[19] = days[19].copy(eventType = EventType.Simple)
        days[28] = days[28].copy(eventType = EventType.Simple)
        days[31] = days[31].copy(eventType = EventType.Simple)

        days[7] = days[7].copy(isToday = true)
    }

    private fun createAfterCurrentMonth(days: MutableList<DayOfMonthUi>, count: Int) {
        repeat(count) {
            val number = it + 1
            val day = DayOfMonthUi(number.toString(), currentMonth = false)
            days.add(day)
        }
    }
}