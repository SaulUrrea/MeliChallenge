package com.saulodev.melichallenge.data.dto


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Picture(
    @SerializedName("id")
    val id: String?,
    @SerializedName("max_size")
    val maxSize: String?,
    @SerializedName("quality")
    val quality: String?,
    @SerializedName("secure_url")
    val secureUrl: String?,
    @SerializedName("size")
    val size: String?,
    @SerializedName("url")
    val url: String?
)