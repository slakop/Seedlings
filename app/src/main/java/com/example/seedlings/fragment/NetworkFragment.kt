package com.example.seedlings.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import com.example.seedlings.MainViewModel
import com.example.seedlings.R
import com.example.seedlings.chat.ChatItem
import com.example.seedlings.data.ViewState
import com.example.seedlings.databinding.FragmentNetworkBinding
import dagger.hilt.android.AndroidEntryPoint

const val KEY = "key"

@AndroidEntryPoint
public final class NetworkFragment : Fragment(R.layout.fragment_network) {
    //    private val binding: FragmentListBinding by lazy {
//        FragmentListBinding.inflate(layoutInflater)
//    }
    private var _binding: FragmentNetworkBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    private var chatList: MutableList<ChatItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when(state) {
                ViewState.None -> toNone()
                ViewState.Loading -> toLoading()
                is ViewState.Content -> toContent(state.profile)
                is ViewState.ContentSend -> toContentSend(state.profile)
                is ViewState.ContentAllSend -> toContentAllSend(state.profile)
            }
        }

        // inflate the layout and bind to the _binding
        _binding = FragmentNetworkBinding.inflate(inflater, container, false)


        binding.button.setOnClickListener {
            viewModel.getProfile()
        }
        binding.save.setOnClickListener {
            viewModel.setProfile(
                ChatItem(
                    id = 1,
                    type = "томат",
                    name = "тест1",
                    manufacturer = "издатель 1",
                    day = 50,
                    planted = 0,
                    counter = 10,
                    date = "2026-01-01"
                )
            )
        }
        binding.delete.setOnClickListener {
            viewModel.delProfile(
                ChatItem(
                    id = 1,
                    type = "томат",
                    name = "тест1",
                    manufacturer = "издатель 1",
                    day = 50,
                    planted = 0,
                    counter = 10,
                    date = "2026-01-01"
                )
            )
        }

        val qqq: MutableList<ChatItem> = mutableListOf()
        qqq.add(ChatItem(id = 1, type = "томат", name = "тест1", manufacturer = "издатель 1", day = 50,planted = 0,counter = 10, date = "2026-01-01"))
        qqq.add(ChatItem(id = 1, type = "огурец", name = "тест1", manufacturer = "издатель 2", day = 50,planted = 0,counter = 10, date = "2026-01-01"))
        qqq.add(ChatItem(id = 1, type = "перец", name = "тест1", manufacturer = "издатель 3", day = 50,planted = 0,counter = 10, date = "2026-01-01"))

        binding.put.setOnClickListener {
            viewModel.putProfile(qqq)
        }

        return binding.root

    }

    private fun toNone() = with(binding) {
//        nameLabel.isVisible = false
//        name.isVisible = false
//        ageLabel.isVisible = false
//        age.isVisible = false
        progressBar.isVisible = false
    }

    private fun toLoading() = with(binding) {
//        nameLabel.isVisible = false
//        name.isVisible = false
//        ageLabel.isVisible = false
//        age.isVisible = false
        progressBar.isVisible = true
    }

    private fun toContent(profile: List<ChatItem>) = with(binding) {
        val index = profile.size-1
//        nameLabel.isVisible = true
//        name.isVisible = true
          name.text = profile[index].type
//        ageLabel.isVisible = true
//        age.isVisible = true
//        age.text = profile[index].age.toString()
        progressBar.isVisible = false

        for (i in 0..index) {
            chatList.add(profile[i])
        }
        setFragmentResult("requestKey",bundleOf("bundleKey" to chatList))
    }

    private fun toContentSend(profile: ChatItem) = with(binding) {
        //nameLabel.isVisible = true
        //name.isVisible = true
        //name.text = profile.name
        //ageLabel.isVisible = true
        //age.isVisible = true
        //age.text = profile.age.toString()
        progressBar.isVisible = false
    }

    private fun toContentAllSend(profile: List<ChatItem>) = with(binding) {
        val index = profile.size-1
        //nameLabel.isVisible = true
        //name.isVisible = true
        //name.text = profile.name
        //ageLabel.isVisible = true
        //age.isVisible = true
        //age.text = profile.age.toString()
        progressBar.isVisible = false
        for (i in 0..index) {
            chatList.add(profile[i])
        }
        setFragmentResult("requestKey",bundleOf("bundleKey" to chatList))

    }
}