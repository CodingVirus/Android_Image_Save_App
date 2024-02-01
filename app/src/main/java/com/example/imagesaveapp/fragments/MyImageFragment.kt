package com.example.imagesaveapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.imagesaveapp.MainActivity
import com.example.imagesaveapp.R
import com.example.imagesaveapp.adapter.ImageSearchAdpater
import com.example.imagesaveapp.data.ImageResult
import com.example.imagesaveapp.databinding.FragmentMyImageBinding
import com.example.imagesaveapp.datainterface.FragmentDataListener

private const val ARG_PARAM1 = "param1"

class MyImageFragment : Fragment() {
    private var param1 = ArrayList<ImageResult>()

    private val binding by lazy { FragmentMyImageBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getParcelableArrayList(ARG_PARAM1)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ImageSearchAdpater(param1)
        adapter.itemClick = object : ImageSearchAdpater.ItemClick {
            override fun onClick(view: View, position: Int, data: ImageResult, like: ImageView) {
                adapter.removeData(position)
                (activity as MainActivity).removeData(data)
            }
        }
        binding.recyclerView.adapter = adapter
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: ArrayList<ImageResult>) =
            MyImageFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_PARAM1, param1)
                }
            }
    }
}