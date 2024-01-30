package com.example.imagesaveapp.datainterface

import android.os.Parcelable
import android.widget.ImageView
import com.example.imagesaveapp.data.ImageResult
import com.example.imagesaveapp.data.SearchImageResponse

interface FragmentDataListener {
    fun onDataReceived(data: ImageResult)
}