package com.saulodev.melichallenge.data.dto


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Country(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)