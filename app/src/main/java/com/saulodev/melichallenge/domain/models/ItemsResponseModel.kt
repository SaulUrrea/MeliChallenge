package com.saulodev.melichallenge.domain.models


import androidx.annotation.Keep

@Keep
data class ItemsResponseModel(
    val countryDefaultTimeZone: String?,
    val pagingModel: PagingModel?,
    val query: String?,
    val items: List<ItemModel>,
    val siteId: String?
)