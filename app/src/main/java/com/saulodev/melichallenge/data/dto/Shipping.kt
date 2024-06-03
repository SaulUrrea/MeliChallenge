package com.saulodev.melichallenge.data.dto


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Shipping(
    @SerializedName("benefits")
    val benefits: Any,
    @SerializedName("free_shipping")
    val freeShipping: Boolean,
    @SerializedName("logistic_type")
    val logisticType: String,
    @SerializedName("mode")
    val mode: String,
    @SerializedName("promise")
    val promise: Any,
    @SerializedName("shipping_score")
    val shippingScore: Int,
    @SerializedName("store_pick_up")
    val storePickUp: Boolean,
    @SerializedName("tags")
    val tags: List<Any>
)