package com.saulodev.melichallenge.data.dto


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class PicturesItemResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("pictures")
    val pictures: List<Picture>?,
    @SerializedName("site_id")
    val siteId: String?
)