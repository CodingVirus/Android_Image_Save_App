package com.example.imagesaveapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VISIBLE
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.imagesaveapp.R
import com.example.imagesaveapp.data.ImageResult
import com.example.imagesaveapp.databinding.FragmentImageSearchBinding
import com.example.imagesaveapp.databinding.ImageItemBinding
import kotlinx.coroutines.flow.combine

class ImageSearchAdpater(private val list: MutableList<ImageResult>) : RecyclerView.Adapter<ImageSearchAdpater.Holder>() {
    interface ItemClick {
        fun onClick(view: View, position: Int) {

        }
    }

    var itemClick : ItemClick? = null

    inner class Holder(binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val img = binding.ivImage
        private val date = binding.txtTime
        private val source = binding.txtWebTitle
        val like = binding.ivLike

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
        list[position].run {
            holder.bind(this)
            holder.itemView.setOnClickListener {
                if (holder.like.visibility == View.INVISIBLE)
                    holder.like.visibility = VISIBLE
                else holder.like.visibility = View.INVISIBLE
                //itemClick?.onClick(it, position)
            }

        }
    }
}