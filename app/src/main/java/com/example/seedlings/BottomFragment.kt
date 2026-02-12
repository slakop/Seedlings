package com.example.seedlings

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class BottomFragment : Fragment(R.layout.fragment_bottom) {
    lateinit var button: Button
    private lateinit var cardFragment: CardFragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button = view.findViewById(R.id.button)
        button.setOnClickListener {
            if (savedInstanceState == null) {
                button.text = "ADD"
                cardFragment = CardFragment()

                activity!!.supportFragmentManager.beginTransaction()
                    .replace(R.id.top_container, cardFragment, "card")
                    .addToBackStack(null)
                    .commit()
            } else {
                cardFragment = activity!!.supportFragmentManager.findFragmentByTag("card") as CardFragment
            }
        }
    }
}