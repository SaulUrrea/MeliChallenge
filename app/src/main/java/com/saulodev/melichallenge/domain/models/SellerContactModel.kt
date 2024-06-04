package com.saulodev.melichallenge.domain.models


import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class SellerContactModel(
    val areaCode: String?,
    val areaCode2: String?,
    val contact: String?,
    val email: String?,
    val otherInfo: String?,
    val phone: String?,
    val phone2: String?,
    val webpage: String?
) : Parcelable