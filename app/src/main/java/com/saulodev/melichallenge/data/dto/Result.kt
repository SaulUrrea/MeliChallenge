package com.saulodev.melichallenge.data.dto


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Result(
    @SerializedName("accepts_mercadopago")
    val acceptsMercadopago: Boolean?,
    @SerializedName("attributes")
    val attributes: List<Attribute>?,
    @SerializedName("available_quantity")
    val availableQuantity: Int?,
    @SerializedName("buying_mode")
    val buyingMode: String?,
    @SerializedName("catalog_listing")
    val catalogListing: Boolean?,
    @SerializedName("category_id")
    val categoryId: String?,
    @SerializedName("condition")
    val condition: String?,
    @SerializedName("currency_id")
    val currencyId: String?,
    @SerializedName("domain_id")
    val domainId: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("listing_type_id")
    val listingTypeId: String?,
    @SerializedName("location")
    val location: Location?,
    @SerializedName("order_backend")
    val orderBackend: Int?,
    @SerializedName("permalink")
    val permalink: String?,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("seller")
    val seller: Seller?,
    @SerializedName("seller_contact")
    val sellerContact: SellerContact?,
    @SerializedName("shipping")
    val shipping: Shipping?,
    @SerializedName("site_id")
    val siteId: String?,
    @SerializedName("stop_time")
    val stopTime: String?,
    @SerializedName("thumbnail")
    val thumbnail: String?,
    @SerializedName("thumbnail_id")
    val thumbnailId: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("use_thumbnail_id")
    val useThumbnailId: Boolean?,
)