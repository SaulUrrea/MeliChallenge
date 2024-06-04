package com.saulodev.melichallenge.data.dto


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Seller(
    @SerializedName("id")
    val id: Int,
    @SerializedName("nickname")
    val nickname: String
)