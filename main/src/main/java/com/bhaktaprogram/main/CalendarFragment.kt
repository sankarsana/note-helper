package com.bhaktaprogram.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.bhaktaprogram.calendarview.CalendarView
import com.bhaktaprogram.calendarview.DayOfMonthUi
import com.bhaktaprogram.calendarview.FakeDaysRepository

class CalendarFragment : Fragment(R.layout.calendar_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val calendar = view.findViewById<CalendarView>(R.id.calendar)
        calendar.setData(FakeDaysRepository.get(), -1)
        calendar.setOnDaySelectListener(::onDaySelected)
    }

    private fun onDaySelected(day: DayOfMonthUi) {
        Log.i("TAG", "selected day: $day")
    }
}