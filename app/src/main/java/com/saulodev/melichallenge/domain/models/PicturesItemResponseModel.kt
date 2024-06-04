package com.saulodev.melichallenge.domain.models


import androidx.annotation.Keep

@Keep
data class PicturesItemResponseModel(
    val id: String?,
    val pictures: List<PictureModel>?,
    val siteId: String?
)