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

    private val frag1 = ImageSearchFragment()
    private val frag2 = MyImageFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportFragmentManager.commit {
            add(R.id.frame_layout, frag1)
            add(R.id.frame_layout, frag2)
            show(frag1)
            hide(frag2)
        }

        with(binding) {
            btnSearchImageFragment.setOnClickListener {
                setFragment(frag1)
            }
            btnMyImageFragment.setOnClickListener {
                setFragment(frag2)
            }
        }
    }

    private fun setFragment(frag: Fragment) {
        supportFragmentManager.commit{
            setReorderingAllowed(true)
            addToBackStack("")
            if (frag == frag1) {
                show(frag1)
                hide(frag2)
            } else {
                show(frag2)
                hide(frag1)
            }
        }
    }
}