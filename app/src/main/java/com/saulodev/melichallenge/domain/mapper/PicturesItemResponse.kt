package com.saulodev.melichallenge.domain.mapper

import com.saulodev.melichallenge.data.dto.Picture
import com.saulodev.melichallenge.data.dto.PicturesItemResponse
import com.saulodev.melichallenge.domain.models.PictureModel
import com.saulodev.melichallenge.domain.models.PicturesItemResponseModel

fun PicturesItemResponse.toPicturesItemResponseModel(): PicturesItemResponseModel {
    return PicturesItemResponseModel(
        id = this.id,
        pictures = this.pictures?.map { it.toPictureModel() },
        siteId = this.siteId
    )
}

fun Picture.toPictureModel(): PictureModel {
    return PictureModel(
        id = this.id,
        maxSize = this.maxSize,
        quality = this.quality,
        secureUrl = this.secureUrl,
        size = this.size,
        url = this.url
    )
}