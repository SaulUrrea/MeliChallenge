package com.saulodev.melichallenge.domain.models


import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class ShippingModel(
    val freeShipping: Boolean?,
    val logisticType: String?,
    val mode: String?,
    val shippingScore: Int?,
    val storePickUp: Boolean?,
) : Parcelable