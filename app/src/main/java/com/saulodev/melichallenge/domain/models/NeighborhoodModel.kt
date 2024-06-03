package com.saulodev.melichallenge.domain.models


import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class NeighborhoodModel(
    val id: String?,
    val name: String?
) : Parcelable