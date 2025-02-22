package com.example.fitnessapp.utils

import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object BotResponse {
    private const val API_KEY = "AIzaSyCD2FYwcNnCpFFHDjG5xnW0lsPMdfHQQ6c" // Replace this with your actual API key
    private val model = GenerativeModel(
        modelName = "gemini-pro",
        apiKey = API_KEY
    )

    suspend fun basicResponse(message: String): String {
        return try {
            withContext(Dispatchers.IO) {
                val prompt = "You are a fitness assistant. Keep the messages brief. Respond to: $message"
                val response = model.generateContent(prompt)
                response.text ?: "Sorry, I couldn't process that."
            }
        } catch (e: Exception) {
            "Sorry, I encountered an error: ${e.message}"
        }
    }
} 