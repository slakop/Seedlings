package com.example.seedlings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.seedlings.data.Profile
import com.example.seedlings.databinding.ActivityMainBinding
import com.example.seedlings.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue
import androidx.lifecycle.ViewModelProvider
import com.example.seedlings.data.ViewState
import com.example.seedlings.databinding.Activity2MainBinding

@AndroidEntryPoint
public final class TestFragment : Fragment(R.layout.fragment_test) {
    private val binding: FragmentListBinding by lazy {
        FragmentListBinding.inflate(layoutInflater)
    }
    //private lateinit var binding: ActivityMainBinding

    //private val viewModel: MainViewModel by viewModels()
    //private lateinit var binding: TestFragment
    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
//        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //viewModel.getProfile()

        //binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(binding.root)

        viewModel.uiState.observe(this) { state ->
            when(state) {
                ViewState.None -> toNone()
                ViewState.Loading -> toLoading()
                is ViewState.Content -> toContent(state.profile)
                is ViewState.ContentSend -> toContentSend(state.profile)
                is ViewState.ContentAllSend -> toContentAllSend(state.profile)
            }
        }

//        binding.button.setOnClickListener {
//            viewModel.getProfile()
//        }
        //binding.save.setOnClickListener {
        //    viewModel.setProfile(Profile(
        //        userId = 1,
        //        name = "Vasya",
        //        age = 25,
        //        //registered = Instant.parse("2023-11-17T11:43:22.306Z"),
        //        //interests = listOf("рыбалка", "корутины", "футбол")
        //    ))
        //}
        //binding.delete.setOnClickListener {
        //    viewModel.delProfile(Profile(
        //        userId = 1,
        //        name = "Vasya",
        //        age = 25,
        //        //registered = Instant.parse("2023-11-17T11:43:22.306Z"),
        //        //interests = listOf("рыбалка", "корутины", "футбол")
        //    ))
        //}

        val qqq: MutableList<Profile> = mutableListOf()
        qqq.add( Profile(userId = 1,name = "Vasya",age = 95))
        qqq.add( Profile(userId = 1,name = "Vasya",age = 96))
        qqq.add( Profile(userId = 1,name = "Vasya",age = 97))

        //binding.put.setOnClickListener {
        //    viewModel.putProfile(qqq)
        //}
    }

    private fun toNone() = with(binding) {
        //nameLabel.isVisible = false
        //name.isVisible = false
        //ageLabel.isVisible = false
        //age.isVisible = false
        //progressBar.isVisible = false
    }

    private fun toLoading() = with(binding) {
        //nameLabel.isVisible = false
        //name.isVisible = false
        //ageLabel.isVisible = false
        //age.isVisible = false
        //progressBar.isVisible = true
    }

    private fun toContent(profile: List<Profile>) = with(binding) {
        //val index = profile.size-1
        //nameLabel.isVisible = true
        //name.isVisible = true
        //name.text = profile[index].name
        //ageLabel.isVisible = true
        //age.isVisible = true
        //age.text = profile[index].age.toString()
        //progressBar.isVisible = false
    }

    private fun toContentSend(profile: Profile) = with(binding) {
        //nameLabel.isVisible = true
        //name.isVisible = true
        //name.text = profile.name
        //ageLabel.isVisible = true
        //age.isVisible = true
        //age.text = profile.age.toString()
        //progressBar.isVisible = false
    }

    private fun toContentAllSend(profile: List<Profile>) = with(binding) {
        //nameLabel.isVisible = true
        //name.isVisible = true
        //name.text = profile.name
        //ageLabel.isVisible = true
        //age.isVisible = true
        //age.text = profile.age.toString()
        //progressBar.isVisible = false
    }
}