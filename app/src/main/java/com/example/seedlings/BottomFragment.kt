package com.example.seedlings

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class BottomFragment : Fragment(R.layout.fragment_bottom) {
    lateinit var button: Button
    private lateinit var cardFragment: CardFragment
    private lateinit var listFragment: ListFragment
    private lateinit var testFragment: TestFragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button = view.findViewById(R.id.add)
        button.setOnClickListener {
            if (savedInstanceState == null) {
                cardFragment = CardFragment()

                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.top_container, cardFragment, "card")
                    .addToBackStack(null)
                    .commit()
            } else {
                cardFragment = requireActivity().supportFragmentManager.findFragmentByTag("card") as CardFragment
            }
        }
        button = view.findViewById(R.id.view)
        button.setOnClickListener {
            if (savedInstanceState == null) {
                listFragment = ListFragment()

                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.top_container, listFragment, "list")
                    .addToBackStack(null)
                    .commit()
            } else {
                cardFragment = requireActivity().supportFragmentManager.findFragmentByTag("list") as CardFragment
            }
        }
        button = view.findViewById(R.id.test)
        button.setOnClickListener {
            if (savedInstanceState == null) {
                testFragment = TestFragment()

                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.top_container, testFragment, "test")
                    .addToBackStack(null)
                    .commit()
            } else {
                cardFragment = requireActivity().supportFragmentManager.findFragmentByTag("test") as CardFragment
            }
        }
    }
}