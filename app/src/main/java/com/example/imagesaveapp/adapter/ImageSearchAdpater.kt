package com.example.imagesaveapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.imagesaveapp.R
import com.example.imagesaveapp.data.ImageResult
import com.example.imagesaveapp.databinding.FragmentImageSearchBinding
import com.example.imagesaveapp.databinding.ImageItemBinding

class ImageSearchAdpater(private val list: MutableList<ImageResult>) : RecyclerView.Adapter<ImageSearchAdpater.Holder>() {

    inner class Holder(binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val img = binding.ivImage
        private val date = binding.txtTime
        private val source = binding.txtWebTitle

        fun bind(items: ImageResult) {
            source.text = items.displaySite
            date.text = items.datetime
            Glide.with(itemView)
                .load(items.thumbnailUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        list[position].run { holder.bind(this) }
    }
}