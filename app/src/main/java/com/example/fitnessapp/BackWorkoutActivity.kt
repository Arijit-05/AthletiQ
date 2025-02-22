package com.example.fitnessapp

import android.content.Context
import android.content.Intent
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

class BackWorkoutActivity : AppCompatActivity() {
    private lateinit var backButton: ImageView
    private lateinit var bentOverRow: CardView
    private lateinit var machineTBarRow: CardView
    private lateinit var pendlayRow: CardView
    private lateinit var bentOverDumbbellRow: CardView
    private lateinit var tripodDumbbellRowCard: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_back_workout)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        backButton = findViewById(R.id.back_btn)
        bentOverRow = findViewById(R.id.bent_over_row_card)
        machineTBarRow = findViewById(R.id.machine_t_bar_row_card)
        pendlayRow = findViewById(R.id.pendlay_row)
        bentOverDumbbellRow = findViewById(R.id.bent_over_dumbbell_row_card)
        tripodDumbbellRowCard = findViewById(R.id.tripod_dumbbell_row_card)

        backButton.setOnClickListener {
            vibrate()
            finish()
        }

        bentOverRow.setOnClickListener {
            startVideoActivity("paCfxhgW6bI")
            vibrate()
        }

        machineTBarRow.setOnClickListener {
            startVideoActivity("kHW23afzaUs")
            vibrate()
        }

        pendlayRow.setOnClickListener {
            startVideoActivity("EDhJ_FTlaXA")
            vibrate()
        }

        bentOverDumbbellRow.setOnClickListener {
            startVideoActivity("DhewkuU")
            vibrate()
        }

        tripodDumbbellRowCard.setOnClickListener {
            startVideoActivity("78Z2mk9LQoI")
            vibrate()
        }

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

    private fun startVideoActivity(videoId: String) {
        val intent = Intent(this@BackWorkoutActivity, VideoActivity::class.java)
        intent.putExtra("VIDEO_ID", videoId)
        startActivity(intent)
    }
}