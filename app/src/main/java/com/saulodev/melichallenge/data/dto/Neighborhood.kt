package com.saulodev.melichallenge.data.dto


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Neighborhood(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)