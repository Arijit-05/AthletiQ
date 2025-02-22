package com.example.fitnessapp

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.data.Message
import com.example.fitnessapp.utils.BotResponse
import com.example.fitnessapp.utils.Constants.RECEIVE_ID
import com.example.fitnessapp.utils.Constants.SEND_ID
import com.example.fitnessapp.utils.MessageManager
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class ChatBotActivity : AppCompatActivity() {
    private lateinit var back_btn: ImageView
    private lateinit var sendBtn: CardView
    private lateinit var etMessage: EditText
    private lateinit var rvMessages: RecyclerView
    private lateinit var messageAdapter: MessagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chat_bot)

        // Initialize views first
        back_btn = findViewById(R.id.back_btn)
        sendBtn = findViewById(R.id.send_card)
        etMessage = findViewById(R.id.et_message)
        rvMessages = findViewById(R.id.rv_messages)

        // Then set up recyclerView and load messages
        setupRecyclerView()
        loadChatHistory()

        // Handle window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.ime())
            view.setPadding(insets.left, insets.top, insets.right, insets.bottom)
            windowInsets
        }

        // Set click listeners
        back_btn.setOnClickListener {
            vibrate()
            saveChatHistory()
            finish()
        }

        sendBtn.setOnClickListener {
            sendMessage()
        }
    }

    private fun setupRecyclerView() {
        messageAdapter = MessagingAdapter()
        rvMessages.adapter = messageAdapter
        rvMessages.layoutManager = LinearLayoutManager(applicationContext)
    }

    private fun loadChatHistory() {
        messageAdapter.messagesList.addAll(MessageManager.loadMessages(this))
        if (messageAdapter.messagesList.isNotEmpty()) {
            rvMessages.scrollToPosition(messageAdapter.messagesList.size - 1)
        }
    }

    private fun saveChatHistory() {
        MessageManager.saveMessages(this, messageAdapter.messagesList)
    }

    override fun onPause() {
        super.onPause()
        saveChatHistory()
    }

    private fun sendMessage() {
        val message = etMessage.text.toString()
        val timeStamp = SimpleDateFormat("HH:mm", Locale.getDefault()).format(System.currentTimeMillis())

        if (message.isNotEmpty()) {
            vibrate()
            etMessage.setText("")

            messageAdapter.messagesList.add(Message(message, SEND_ID, timeStamp))
            messageAdapter.notifyItemInserted(messageAdapter.messagesList.size - 1)
            rvMessages.scrollToPosition(messageAdapter.messagesList.size - 1)

            lifecycleScope.launch {
                val response = BotResponse.basicResponse(message)
                receiveMessage(response)
            }
        }
    }

    private fun receiveMessage(response: String) {
        val timeStamp = SimpleDateFormat("HH:mm", Locale.getDefault()).format(System.currentTimeMillis())
        
        messageAdapter.messagesList.add(Message(response, RECEIVE_ID, timeStamp))
        messageAdapter.notifyItemInserted(messageAdapter.messagesList.size - 1)
        rvMessages.scrollToPosition(messageAdapter.messagesList.size - 1)
    }

    private fun vibrate() {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (vibrator.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val vibrationEffect = VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE)
                vibrator.vibrate(vibrationEffect)
            } else {
                vibrator.vibrate(50)
            }
        }
    }
}