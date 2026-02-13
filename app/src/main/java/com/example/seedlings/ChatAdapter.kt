package com.example.seedlings

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.seedlings.databinding.ChatItemBinding
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import kotlin.random.Random

class ChatAdapter: ListAdapter<ChatItem, ChatViewHolder>(ChatDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding = ChatItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chatItem = getItem(position)
        val binding = holder.binding

        when (chatItem.id % 1) {
            0-> binding.imageAvatar.setImageResource(R.drawable.avatar_1)
            else -> binding.imageAvatar.setImageResource(R.drawable.avatar_1)
        }

        binding.title.setText(chatItem.title)
        binding.author.setText(chatItem.author)
        binding.message.setText(chatItem.message)
        if (chatItem.author == "...typing") binding.message.visibility = INVISIBLE
        binding.verifiedIcon.visibility = if (chatItem.verifiedIcon) VISIBLE else INVISIBLE
        binding.muteIcon.visibility = if (chatItem.muteIcon) VISIBLE else INVISIBLE
        binding.scamPatch.visibility = if (chatItem.scamPatch) VISIBLE else INVISIBLE
        binding.counter.visibility = if (chatItem.counter > 0) VISIBLE else INVISIBLE
        binding.counter.text = if (binding.counter.visibility == VISIBLE) chatItem.counter.toString() else ""
        if (Random.nextBoolean()) binding.counter.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#C5C9CC"))
        binding.checkIcon.visibility = if (chatItem.check && !chatItem.read) VISIBLE else INVISIBLE
        binding.readIcon.visibility = if (!chatItem.check && chatItem.read) VISIBLE else INVISIBLE
        binding.time.setText(chatItem.time)
    }
 }
