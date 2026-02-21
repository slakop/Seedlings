package com.example.seedlings.fragment

import android.os.Bundle
import android.view.View
import android.widget.CalendarView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.seedlings.R

class CalendarFragment : Fragment(R.layout.fragment_calendar)
{
    private lateinit var listFragment: ListFragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val calendar: CalendarView = view.findViewById(R.id.calendar)
        val dateView: TextView = view.findViewById(R.id.date_view)

//        calendar.setOnDateChangeListener { _, year, month, dayOfMonth ->
//            val date = (dayOfMonth.toString() + "-" + (month + 1) + "-" + year)
//            dateView.text = date
//        }
//
        calendar.setOnDateChangeListener {_, year, month, dayOfMonth ->
            val date = (dayOfMonth.toString() + "-" + (month + 1) + "-" + year)
            listFragment = ListFragment()

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.top_container, listFragment, "list")
                .addToBackStack(null)
                .commit()
        }
    }
}