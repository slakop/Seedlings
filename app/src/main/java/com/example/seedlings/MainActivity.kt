package com.example.seedlings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.seedlings.databinding.ActivityNavBinding
//import com.example.seedlings.fragment.BottomFragment
import com.example.seedlings.fragment.CalendarFragment
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

//@AndroidEntryPoint
//class MainActivity : AppCompatActivity() {
//    private lateinit var calendarFragment: CalendarFragment
//    private lateinit var bottomFragment: BottomFragment
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//
//        if (savedInstanceState == null) {
//            calendarFragment = CalendarFragment()
//            bottomFragment = BottomFragment()
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.top_container, calendarFragment, "calendar")
//                .replace(R.id.bottom_container, bottomFragment, "bottom")
//                .commit()
//        } else {
//            calendarFragment = supportFragmentManager.findFragmentByTag("calendar") as CalendarFragment
//            bottomFragment = supportFragmentManager.findFragmentByTag("bottom") as BottomFragment
//        }
//    }
//}
@AndroidEntryPoint
class NavActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNavBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment_container)
        binding.navView.setupWithNavController(navController)
    }
}

