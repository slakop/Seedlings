package com.example.seedlings

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.example.seedlings.databinding.Activity2MainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.datetime.Instant
import com.example.seedlings.data.Profile
import com.example.seedlings.data.ViewState
//import com.example.seedlings.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: Activity2MainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Activity2MainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.uiState.observe(this) { state ->
            when(state) {
                ViewState.None -> toNone()
                ViewState.Loading -> toLoading()
                is ViewState.Content -> toContent(state.profile)
                is ViewState.ContentSend -> toContentSend(state.profile)
                is ViewState.ContentAllSend -> toContentAllSend(state.profile)
            }
        }

        binding.button.setOnClickListener {
            viewModel.getProfile()
        }
        binding.save.setOnClickListener {
            viewModel.setProfile(Profile(
                userId = 1,
                name = "Vasya",
                age = 25,
                //registered = Instant.parse("2023-11-17T11:43:22.306Z"),
                //interests = listOf("рыбалка", "корутины", "футбол")
            ))
        }
        binding.delete.setOnClickListener {
            viewModel.delProfile(Profile(
                userId = 1,
                name = "Vasya",
                age = 25,
                //registered = Instant.parse("2023-11-17T11:43:22.306Z"),
                //interests = listOf("рыбалка", "корутины", "футбол")
            ))
        }

        val qqq: MutableList<Profile> = mutableListOf()
        qqq.add( Profile(userId = 1,name = "Vasya",age = 95))
        qqq.add( Profile(userId = 1,name = "Vasya",age = 96))
        qqq.add( Profile(userId = 1,name = "Vasya",age = 97))

        //binding.put.setOnClickListener {
        //    viewModel.putProfile(qqq)
        //}
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