package com.example.fitnessapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.data.Message
import com.example.fitnessapp.utils.Constants.RECEIVE_ID
import com.example.fitnessapp.utils.Constants.SEND_ID
import android.widget.TextView

class MessagingAdapter: RecyclerView.Adapter<MessagingAdapter.MessageViewHolder>() {

    var messagesList = mutableListOf<Message>()

    inner class MessageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvMessage = itemView.findViewById<TextView>(R.id.tv_message)
        val tvBotMessage = itemView.findViewById<TextView>(R.id.tv_bot_message)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false))
    }

    override fun getItemCount(): Int {
        return messagesList.size
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val currentMessage = messagesList[position]

        when (currentMessage.id) {
            SEND_ID -> {
                holder.tvMessage.apply {
                    text = currentMessage.message
                    visibility = View.VISIBLE
                }
                holder.tvBotMessage.visibility = View.GONE
            }
            RECEIVE_ID -> {
                holder.tvBotMessage.apply {
                    text = currentMessage.message
                    visibility = View.VISIBLE
                }
                holder.tvMessage.visibility = View.GONE
            }
        }
    }
}
