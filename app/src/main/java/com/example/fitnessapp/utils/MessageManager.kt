package com.example.fitnessapp.utils

import android.content.Context
import com.example.fitnessapp.data.Message
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object MessageManager {
    private const val PREF_NAME = "chat_history"
    private const val KEY_MESSAGES = "messages"

    fun saveMessages(context: Context, messages: List<Message>) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val gson = Gson()
        val json = gson.toJson(messages)
        prefs.edit().putString(KEY_MESSAGES, json).apply()
    }

    fun loadMessages(context: Context): List<Message> {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val gson = Gson()
        val json = prefs.getString(KEY_MESSAGES, null) ?: return emptyList()
        val type = object : TypeToken<List<Message>>() {}.type
        return gson.fromJson(json, type)
    }
} 