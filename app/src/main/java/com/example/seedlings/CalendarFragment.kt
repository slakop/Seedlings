package com.example.seedlings

import android.os.Bundle
import android.view.View
import android.widget.CalendarView
import android.widget.TextView
import androidx.fragment.app.Fragment

class CalendarFragment : Fragment(R.layout.fragment_calendar)
{
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val calendar: CalendarView = view.findViewById(R.id.calendar)
        val dateView: TextView = view.findViewById(R.id.date_view)

        calendar.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val date = (dayOfMonth.toString() + "-" + (month + 1) + "-" + year)
            dateView.text = date
        }
    }
}

