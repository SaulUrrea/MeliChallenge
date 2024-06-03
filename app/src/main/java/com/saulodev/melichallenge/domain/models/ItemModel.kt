package com.saulodev.melichallenge.domain.models


import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class ItemModel(
    val acceptsMercadopago: Boolean?,
    val attributeModels: List<AttributeModel>?,
    val availableQuantity: Int?,
    val buyingMode: String?,
    val catalogListing: Boolean?,
    val categoryId: String?,
    val condition: String?,
    val currencyId: String?,
    val domainId: String?,
    val id: String?,
    val listingTypeId: String?,
    val locationModel: LocationModel?,
    val orderBackend: Int?,
    val permalink: String?,
    val price: Double?,
    val sellerModel: SellerModel?,
    val sellerContactModel: SellerContactModel?,
    val shippingModel: ShippingModel?,
    val siteId: String?,
    val stopTime: String?,
    val thumbnail: String?,
    val thumbnailId: String?,
    val title: String?,
    val useThumbnailId: Boolean?,
) : Parcelable