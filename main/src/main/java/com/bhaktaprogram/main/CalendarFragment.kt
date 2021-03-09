package com.bhaktaprogram.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.bhaktaprogram.calendarview.CalendarView
import com.bhaktaprogram.calendarview.FakeDaysRepository
import java.util.*

class CalendarFragment : Fragment(R.layout.calendar_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val calendar = view.findViewById<CalendarView>(R.id.calendar)
        calendar.setMonthParams(9, Calendar.MARCH, 2021, Calendar.MONDAY)
        calendar.setEvents(FakeDaysRepository.getEvents())
        calendar.setOnDaySelectListener(::onDaySelected)
    }

    private fun onDaySelected(calendar: Calendar) {
        Log.i("TAG", "selected day: ${calendar[Calendar.DAY_OF_MONTH]}")
    }
}