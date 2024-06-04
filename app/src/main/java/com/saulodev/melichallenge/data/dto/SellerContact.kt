package com.saulodev.melichallenge.data.dto


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class SellerContact(
    @SerializedName("area_code")
    val areaCode: String,
    @SerializedName("area_code2")
    val areaCode2: String,
    @SerializedName("contact")
    val contact: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("other_info")
    val otherInfo: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("phone2")
    val phone2: String,
    @SerializedName("webpage")
    val webpage: String
)