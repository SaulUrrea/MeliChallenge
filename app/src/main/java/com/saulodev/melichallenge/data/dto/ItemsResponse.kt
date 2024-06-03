package com.saulodev.melichallenge.data.dto


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ItemsResponse(
    @SerializedName("country_default_time_zone")
    val countryDefaultTimeZone: String?,
    @SerializedName("paging")
    val paging: Paging?,
    @SerializedName("query")
    val query: String?,
    @SerializedName("results")
    val results: List<Result>?,
    @SerializedName("site_id")
    val siteId: String?
)