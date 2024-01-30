package com.example.imagesaveapp.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class SearchImage(val response: SearchImageResponse)

data class SearchImageResponse(
    @SerializedName("meta")
    val meta: SearchMeta?,
    @SerializedName("documents")
    val docs: ArrayList<ImageResult>?
)

@Parcelize
data class ImageResult(
    @SerializedName("collection")
    val collection: String,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("width")
    val width: Int,
    @SerializedName("height")
    val height: Int,
    @SerializedName("display_sitename")
    val displaySite: String,
    @SerializedName("doc_url")
    val docUrl: String,
    @SerializedName("datetime")
    val datetime: String,
) : Parcelable

data class SearchMeta(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("pageable_count")
    val pageableCount: Int,
    @SerializedName("is_end")
    val isEnd: Boolean,
)