package com.example.seedlings.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.seedlings.MainViewModel
import com.example.seedlings.R
import com.example.seedlings.data.Profile
import com.example.seedlings.data.ViewState
import com.example.seedlings.databinding.FragmentNetworkBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
public final class NetworkFragment : Fragment(R.layout.fragment_network) {
    //    private val binding: FragmentListBinding by lazy {
//        FragmentListBinding.inflate(layoutInflater)
//    }
    private var _binding: FragmentNetworkBinding? = null
    private val binding get() = _binding!!

    //private lateinit var binding: Activity2MainBinding

    //private val viewModel: MainViewModel by viewModels()
    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

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
                Profile(
                    userId = 1,
                    name = "Vasya",
                    age = 55,
                )
            )
        }
        binding.delete.setOnClickListener {
            viewModel.delProfile(
                Profile(
                    userId = 1,
                    name = "Vasya",
                    age = 25,
                )
            )
        }

        val qqq: MutableList<Profile> = mutableListOf()
        qqq.add(Profile(userId = 1, name = "Vasya", age = 95))
        qqq.add(Profile(userId = 1, name = "Vasya", age = 96))
        qqq.add(Profile(userId = 1, name = "Vasya", age = 97))

        binding.put.setOnClickListener {
            viewModel.putProfile(qqq)
        }

        return binding.root

    }

    private fun toNone() = with(binding) {
        nameLabel.isVisible = false
        name.isVisible = false
        ageLabel.isVisible = false
        age.isVisible = false
        progressBar.isVisible = false
    }

    private fun toLoading() = with(binding) {
        nameLabel.isVisible = false
        name.isVisible = false
        ageLabel.isVisible = false
        age.isVisible = false
        progressBar.isVisible = true
    }

    private fun toContent(profile: List<Profile>) = with(binding) {
        val index = profile.size-1
        nameLabel.isVisible = true
        name.isVisible = true
        name.text = profile[index].name
        ageLabel.isVisible = true
        age.isVisible = true
        age.text = profile[index].age.toString()
        progressBar.isVisible = false
    }

    private fun toContentSend(profile: Profile) = with(binding) {
        nameLabel.isVisible = true
        name.isVisible = true
        name.text = profile.name
        ageLabel.isVisible = true
        age.isVisible = true
        age.text = profile.age.toString()
        progressBar.isVisible = false
    }

    private fun toContentAllSend(profile: List<Profile>) = with(binding) {
        nameLabel.isVisible = true
        name.isVisible = true
        //name.text = profile.name
        ageLabel.isVisible = true
        age.isVisible = true
        //age.text = profile.age.toString()
        progressBar.isVisible = false
    }
}