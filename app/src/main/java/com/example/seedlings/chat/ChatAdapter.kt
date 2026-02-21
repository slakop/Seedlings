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

        when (chatItem.id % 3) {
            0-> binding.imageAvatar.setImageResource(R.drawable.cucumber)
            1-> binding.imageAvatar.setImageResource(R.drawable.tomato)
            2-> binding.imageAvatar.setImageResource(R.drawable.pepper)
            else -> binding.imageAvatar.setImageResource(R.drawable.cucumber)
        }

        binding.title.setText(chatItem.title)
        binding.author.setText(chatItem.author)
        binding.message.setText(chatItem.message)
        if (chatItem.author == "...typing") binding.message.visibility = View.INVISIBLE
        binding.verifiedIcon.visibility = if (chatItem.verifiedIcon) View.VISIBLE else View.INVISIBLE
        binding.muteIcon.visibility = if (chatItem.muteIcon) View.VISIBLE else View.INVISIBLE
        binding.scamPatch.visibility = if (chatItem.scamPatch) View.VISIBLE else View.INVISIBLE
        binding.counter.visibility = if (chatItem.counter > 0) View.VISIBLE else View.INVISIBLE
        binding.counter.text = if (binding.counter.visibility == View.VISIBLE) chatItem.counter.toString() else ""
        if (Random.Default.nextBoolean()) binding.counter.backgroundTintList = ColorStateList.valueOf(
            Color.parseColor("#C5C9CC"))
        binding.checkIcon.visibility = if (chatItem.check && !chatItem.read) View.VISIBLE else View.INVISIBLE
        binding.readIcon.visibility = if (!chatItem.check && chatItem.read) View.VISIBLE else View.INVISIBLE
        binding.time.setText(chatItem.time)
    }
 }