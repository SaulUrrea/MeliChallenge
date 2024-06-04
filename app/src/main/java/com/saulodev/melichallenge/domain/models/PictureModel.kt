package com.saulodev.melichallenge.domain.models


import androidx.annotation.Keep

@Keep
data class PictureModel(
    val id: String?,
    val maxSize: String?,
    val quality: String?,
    val secureUrl: String?,
    val size: String?,
    val url: String?
)