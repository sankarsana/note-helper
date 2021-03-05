package com.bhaktaprogram.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bhaktaprogram.main.custom.view.CalendarView
import com.bhaktaprogram.main.custom.view.FakeDaysRepository

class CalendarFragment : Fragment(R.layout.calendar_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val calendar = view.findViewById<CalendarView>(R.id.calendar)
        calendar.setData(FakeDaysRepository.get())
    }
}