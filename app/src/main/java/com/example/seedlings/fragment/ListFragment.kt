package com.example.seedlings.fragment

import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.seedlings.R
import com.example.seedlings.chat.ChatAdapter
import com.example.seedlings.chat.ChatItem
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
    private lateinit var networkFragment: NetworkFragment

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
//        var id = 0
//        val type = arrayOf("огурец" , "томат","перец")
//        val name = arrayOf("Nikolay", "Anno", "You")
//        val manufacturer = arrayOf("издатель 1", "издатель 2", "издатель 3")
//        for (i in 1..size) {
//            chatList.add(
//                ChatItem(
//                    id++,
//                    type[Random.nextInt(type.size)],
//                    name[Random.nextInt(name.size)],
//                    manufacturer[Random.nextInt(manufacturer.size)],
//                    Random.nextInt(100),
//                    0,
//                    Random.nextInt(100),
//                    "2026-01-01"
//                )
//            )
//        }

//        activity?.supportFragmentManager?.setFragmentResultListener(
//            "key",
//            viewLifecycleOwner,
//            { requestKey, chatList ->
//                TODO("Not yet implemented")
//            })

        setFragmentResultListener("requestKey") { requestKey, bundle ->
            // We use a String here, but any type that can be put in a Bundle is supported,
            // remember that putExtra from intents uses a Bundle underlying, so anything
            // you putExtra works here too!
            val chatList = bundle.getSerializable("bundleKey") as MutableList<ChatItem>
            adapter.submitList(chatList)
            adapter.notifyDataSetChanged()
            // Do something with the result
        }

    //    val networkFragment = NetworkFragment.newInstance(chatList)
    //    networkFragment=NetworkFragment()
    //    var a = networkFragment.test()
    //    chatList = NetworkFragment().test()
    //    adapter.submitList(chatList)
    //    adapter.notifyDataSetChanged()
    }
}