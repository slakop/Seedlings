package com.example.seedlings

import android.content.Context
import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.seedlings.R
import com.example.seedlings.databinding.FragmentListBinding
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import kotlin.random.Random

class ListFragment : Fragment(R.layout.fragment_list) {
    private val binding: FragmentListBinding by lazy {
        FragmentListBinding.inflate(layoutInflater)
    }
    private var chatList: MutableList<ChatItem> = mutableListOf()
    private lateinit var adapter: ChatAdapter
    //var id = 0
    var previousItemsCount = 0
    private var pageLoad = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        adapter = ChatAdapter()
        binding.recyclerView.adapter = adapter

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        removeItem(position)
                    }

                    ItemTouchHelper.RIGHT -> {
                        removeItem(position)
                    }
                }
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                RecyclerViewSwipeDecorator.Builder(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                ).create().decorate()
                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }
        })
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
        createItems(6)
        setScrollListener()

        return binding.root
    }

    private fun setScrollListener(){
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val itemsCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastCompletelyVisibleItemPosition()
                if (pageLoad && itemsCount > previousItemsCount) {
                    pageLoad = false
                    previousItemsCount = itemsCount
                }
                if (!pageLoad && lastVisibleItem >= itemsCount - 5) {
                    getNextPage()
                }
            }
        })
    }

    private fun getNextPage() {
        pageLoad = true
        createItems(6)
    }

    private fun removeItem(position: Int){
        val item = adapter.currentList[position]
        chatList.apply {
            remove(item)
        }
        adapter.notifyItemRemoved(position)
    }

    private fun createItems(size: Int){
        var id = 0
        val title = arrayOf("Dima Murantsev" , "SnejUgal News","Catbird","just design", "R4IN80W", "Yes. No")
        val author = arrayOf("Nikolay", "Anno", "You", "...typing"," ")
        val message = arrayOf("you are welcome :)", "F", "I want pizza", "du biest mein sonnechein", "I see all :))", "That`s better")
        for (i in 1..size) {
            chatList.add(
                ChatItem(id++,title[Random.nextInt(title.size)],author[Random.nextInt(author.size)],message[Random.nextInt(author.size)],
                    Random.nextBoolean(), Random.nextBoolean(), Random.nextBoolean(), Random.nextInt(100),
                    Random.nextBoolean(),Random.nextBoolean(),
                    Random.nextInt(24).toString() + ":" + (Random.nextInt(60).toString().padStart(2, '0'))
                )
            )
        }
        adapter.submitList(chatList)
        adapter.notifyDataSetChanged()
    }
}