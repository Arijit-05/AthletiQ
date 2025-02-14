package com.example.fitnessapp

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ChestWorkoutActivity : AppCompatActivity() {
    private lateinit var backButton: ImageView
    private lateinit var dumbbellBenchPressCard: CardView
    private lateinit var inclineBenchPressCard: CardView
    private lateinit var cableIronCrossCard: CardView
    private lateinit var chestDipCard: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chest_workout)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        backButton = findViewById(R.id.back_btn)
        dumbbellBenchPressCard = findViewById(R.id.dumbbell_bench_press_card)
        inclineBenchPressCard = findViewById(R.id.incline_bench_press_card)
        cableIronCrossCard = findViewById(R.id.cable_iron_cross_card)
        chestDipCard = findViewById(R.id.chest_dip_card)

        backButton.setOnClickListener {
            vibrate()
            finish()
        }
    }

    private fun vibrate() {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (vibrator.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val vibrationEffect =
                    VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE)
                vibrator.vibrate(vibrationEffect)
            } else {
                vibrator.vibrate(50) // Vibrate for 50 milliseconds
            }
        }
    }
}