package com.saulodev.melichallenge.data.dto


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Location(
    @SerializedName("address_line")
    val addressLine: String,
    @SerializedName("city")
    val city: City,
    @SerializedName("country")
    val country: Country,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("neighborhood")
    val neighborhood: Neighborhood,
    @SerializedName("zip_code")
    val zipCode: String
)