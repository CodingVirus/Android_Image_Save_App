package com.example.imagesaveapp.datainterface

import android.os.Parcelable
import android.widget.ImageView

interface FragmentDataListener {
    fun onDataReceived(data: ImageView)
}