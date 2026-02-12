package com.example.seedlings

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var topFragment: CalendarFragment
    private lateinit var bottomFragment: BottomFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (savedInstanceState == null) {
            topFragment = CalendarFragment()
            bottomFragment = BottomFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.top_container, topFragment, "top")
                .replace(R.id.bottom_container, bottomFragment, "bottom")
                .commit()
        } else {
            topFragment = supportFragmentManager.findFragmentByTag("top") as CalendarFragment
            bottomFragment = supportFragmentManager.findFragmentByTag("bottom") as BottomFragment
        }
    }
}