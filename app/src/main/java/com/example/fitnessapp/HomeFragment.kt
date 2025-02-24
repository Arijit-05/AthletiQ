package com.example.fitnessapp

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.getSystemService
import com.google.android.material.search.SearchBar

class HomeFragment : Fragment() {
    private lateinit var userIconCard: CardView
    private lateinit var chestCard: CardView
    private lateinit var backCard: CardView
    private lateinit var aiBtn: Button
    private lateinit var searchBar: SearchBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        userIconCard = view.findViewById(R.id.user_icon_card)
        chestCard = view.findViewById(R.id.chest_workout_card)
        backCard = view.findViewById(R.id.back_workout_card)
        aiBtn = view.findViewById(R.id.ai_btn)
        searchBar = view.findViewById(R.id.search_bar)

        userIconCard.setOnClickListener {
            startActivity(Intent(context, UserActivity::class.java))
            vibrate()
        }

        chestCard.setOnClickListener {
            startActivity(Intent(context, ChestWorkoutActivity::class.java))
            vibrate()
        }

        backCard.setOnClickListener {
            startActivity(Intent(context, BackWorkoutActivity::class.java))
            vibrate()
        }

        aiBtn.setOnClickListener {
            startActivity(Intent(context, ChatBotActivity::class.java))
            vibrate()
        }

        searchBar.setOnClickListener {
            val intent = Intent(context, SearchResultActivity::class.java)
            intent.putExtra("query", searchBar.text.toString())
            startActivity(intent)
            vibrate()
        }

        return view
    }

    private fun loadFragment(fragment: Fragment) {
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

    private fun vibrate() {
        val vibrator = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (vibrator.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val vibrationEffect = VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE)
                vibrator.vibrate(vibrationEffect)
            } else {
                vibrator.vibrate(50) // Vibrate for 50 milliseconds
            }
        }
    }
}