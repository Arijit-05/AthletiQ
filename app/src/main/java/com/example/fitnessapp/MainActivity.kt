package com.example.fitnessapp

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    private lateinit var home: ImageView
    private lateinit var ai: ImageView
    private lateinit var report: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        home = findViewById(R.id.home)
        ai = findViewById(R.id.ai)
        report = findViewById(R.id.report)

        loadFragment(HomeFragment())

        home.setOnClickListener {
            loadFragment(HomeFragment())
            home.setImageResource(R.drawable.house_filled)
            ai.setImageResource(R.drawable.ai)
            report.setImageResource(R.drawable.document)
            vibrate()
        }

        ai.setOnClickListener {
            ai.setImageResource(R.drawable.ai_filled)
            home.setImageResource(R.drawable.house)
            report.setImageResource(R.drawable.document)
            vibrate()
        }

        report.setOnClickListener {
            loadFragment(StatsFragment())
            report.setImageResource(R.drawable.document_filled)
            home.setImageResource(R.drawable.house)
            ai.setImageResource(R.drawable.ai)
            vibrate()
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

    private fun vibrate() {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
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