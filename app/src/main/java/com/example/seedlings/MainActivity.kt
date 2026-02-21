package com.example.seedlings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.seedlings.fragment.BottomFragment
import com.example.seedlings.fragment.CalendarFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var calendarFragment: CalendarFragment
    private lateinit var bottomFragment: BottomFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (savedInstanceState == null) {
            calendarFragment = CalendarFragment()
            bottomFragment = BottomFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.top_container, calendarFragment, "calendar")
                .replace(R.id.bottom_container, bottomFragment, "bottom")
                .commit()
        } else {
            calendarFragment = supportFragmentManager.findFragmentByTag("calendar") as CalendarFragment
            bottomFragment = supportFragmentManager.findFragmentByTag("bottom") as BottomFragment
        }
    }
}
