package com.bhaktaprogram.main.custom.view

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
    }

    private fun createAfterCurrentMonth(days: MutableList<DayOfMonthUi>, count: Int) {
        repeat(count) {
            val number = it + 1
            val day = DayOfMonthUi(number.toString(), currentMonth = false)
            days.add(day)
        }
    }
}