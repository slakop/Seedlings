package com.example.seedlings.chat

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.seedlings.R
import com.example.seedlings.databinding.ChatItemBinding
import kotlin.random.Random

class ChatAdapter: ListAdapter<ChatItem, ChatViewHolder>(ChatDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding = ChatItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chatItem = getItem(position)
        val binding = holder.binding

        when (chatItem.type) {
            "огурец"-> binding.imageAvatar.setImageResource(R.drawable.cucumber)
            "томат"-> binding.imageAvatar.setImageResource(R.drawable.tomato)
            "перец"-> binding.imageAvatar.setImageResource(R.drawable.pepper)
            else -> binding.imageAvatar.setImageResource(R.drawable.cucumber)
        }

        binding.type.setText(chatItem.type)
        binding.name.setText(chatItem.name)
        binding.manufacturer.setText(chatItem.manufacturer)
        binding.planted.visibility = if (chatItem.planted==1) View.VISIBLE else View.INVISIBLE
        binding.counter.visibility = if (chatItem.counter > 0) View.VISIBLE else View.INVISIBLE
        binding.counter.text = if (binding.counter.visibility == View.VISIBLE) chatItem.counter.toString() else ""
        if (chatItem.planted==0) binding.counter.backgroundTintList = ColorStateList.valueOf(
            Color.parseColor("#C5C9CC"))
        binding.date.setText(chatItem.date)
    }
 }