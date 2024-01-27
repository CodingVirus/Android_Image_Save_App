package com.example.imagesaveapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.lifecycleScope
import com.example.imagesaveapp.Constants
import com.example.imagesaveapp.adapter.ImageSearchAdpater
import com.example.imagesaveapp.data.ImageResult
import com.example.imagesaveapp.databinding.FragmentImageSearchBinding
import com.example.imagesaveapp.retrofit.NetWorkClient
import kotlinx.coroutines.launch

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ImageSearchFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private val binding by lazy { FragmentImageSearchBinding.inflate(layoutInflater) }

    private var items = mutableListOf <ImageResult>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        view?.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        Log.i("Minyong", "Test!!")
                    }
                }
                return true
            }

        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                communicateNetWork(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }


    private fun communicateNetWork(query: String?) = lifecycleScope.launch {
        val test = NetWorkClient.api.getImage(Constants.AUTH_HEADER, query, "accuracy", 1, 20).docs

        items = test!!
        items.sortByDescending { it.datetime }
        binding.recyclerView.adapter = ImageSearchAdpater(items)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ImageSearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

