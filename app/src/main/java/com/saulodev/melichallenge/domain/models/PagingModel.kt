package com.saulodev.melichallenge.domain.models


import androidx.annotation.Keep

@Keep
data class PagingModel(
    val limit: Int?,
    val offset: Int?,
    val primaryResults: Int?,
    val total: Int?
)