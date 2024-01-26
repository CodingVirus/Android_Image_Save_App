package com.example.imagesaveapp

import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.util.Log
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.imagesaveapp.data.ImageResult
import com.example.imagesaveapp.databinding.ActivityMainBinding
import com.example.imagesaveapp.fragments.ImageSearchFragment
import com.example.imagesaveapp.fragments.MyImageFragment
import com.example.imagesaveapp.retrofit.NetWorkClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    var items = mutableListOf <ImageResult>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setFragment(ImageSearchFragment())

        with(binding) {
            btnSearchImageFragment.setOnClickListener {
                setFragment(ImageSearchFragment())
            }
            btnMyImageFragment.setOnClickListener {
                setFragment(MyImageFragment())
            }
        }

        communicateNetWork()

    }

    private fun setFragment(frag: Fragment) {
        supportFragmentManager.commit{
            replace(R.id.frame_layout, frag)
            setReorderingAllowed(true)
            addToBackStack("")
        }
    }


    private fun communicateNetWork() = lifecycleScope.launch {
        val test = NetWorkClient.api.getImage(Constants.AUTH_HEADER, "아이브 리즈", "accuracy", 1, 5).docs

        items = test!!


        runOnUiThread {
            Glide.with(this@MainActivity)
                .load(items[2].imageUrl)
                .into(binding.ivTest)
        }

    }
}