package com.example.imagesaveapp

import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.os.Parcelable
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.imagesaveapp.data.ImageResult
import com.example.imagesaveapp.databinding.ActivityMainBinding
import com.example.imagesaveapp.datainterface.FragmentDataListener
import com.example.imagesaveapp.fragments.ImageSearchFragment
import com.example.imagesaveapp.fragments.MyImageFragment
import com.example.imagesaveapp.retrofit.NetWorkClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(), FragmentDataListener {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var items = ArrayList<ImageResult>()
    private val frag1 = ImageSearchFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setFragment(frag1)

        with(binding) {
            btnSearchImageFragment.setOnClickListener {
                setFragment(frag1)
            }
            btnMyImageFragment.setOnClickListener {
                val frag = MyImageFragment.newInstance(items)
                setFragment(frag)
            }
        }
    }

    private fun setFragment(frag: Fragment) {
        supportFragmentManager.commit{
            setReorderingAllowed(true)
            addToBackStack("")
            replace(R.id.frame_layout, frag)
        }
    }

    override fun onDataReceived(data: ImageResult) {
        items.add(data)
        saveData()
    }

    private fun saveData() {
        val pref = getSharedPreferences("pref", 0)
        val edit = pref.edit()
       // edit.putString("name", items)
    }

    private fun loadData() {
        val pref = getSharedPreferences("pref", 0)
        //testItems = pref.getString("name", "")
    }
}