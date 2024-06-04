package com.saulodev.melichallenge.mocks

import com.saulodev.melichallenge.data.dto.PicturesItemResponse
import com.saulodev.melichallenge.domain.models.PictureModel
import com.saulodev.melichallenge.domain.models.PicturesItemResponseModel

class PicturesMock {
    companion object {

        fun getPicturesResponseDTO(): PicturesItemResponse {
            return PicturesItemResponse(
                id = "123456",
                pictures = listOf(),
                siteId = "MLA"
            )
        }

        fun getPicturesResponse(): PicturesItemResponseModel {
            return PicturesItemResponseModel(
                id = "123456",
                pictures = listOf(
                    getPictures()
                ),
                siteId = "MLA"
            )
        }

        fun getPictures(): PictureModel {
            return PictureModel(
                id = "645554-MLA76554275740_052024",
                maxSize = "1200x900",
                quality = "",
                secureUrl = "https://http2.mlstatic.com/D_645554-MLA76554275740_052024-O.jpg",
                size = "500x375",
                url = "http://http2.mlstatic.com/D_645554-MLA76554275740_052024-O.jpg"
            )
        }
    }
}

