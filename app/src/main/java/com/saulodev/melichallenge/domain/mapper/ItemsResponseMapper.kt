package com.saulodev.melichallenge.domain.mapper

import com.saulodev.melichallenge.data.dto.Attribute
import com.saulodev.melichallenge.data.dto.City
import com.saulodev.melichallenge.data.dto.Country
import com.saulodev.melichallenge.data.dto.ItemsResponse
import com.saulodev.melichallenge.data.dto.Location
import com.saulodev.melichallenge.data.dto.Neighborhood
import com.saulodev.melichallenge.data.dto.Paging
import com.saulodev.melichallenge.data.dto.Result
import com.saulodev.melichallenge.data.dto.Seller
import com.saulodev.melichallenge.data.dto.SellerContact
import com.saulodev.melichallenge.data.dto.Shipping
import com.saulodev.melichallenge.domain.models.AttributeModel
import com.saulodev.melichallenge.domain.models.CityModel
import com.saulodev.melichallenge.domain.models.CountryModel
import com.saulodev.melichallenge.domain.models.ItemModel
import com.saulodev.melichallenge.domain.models.ItemsResponseModel
import com.saulodev.melichallenge.domain.models.LocationModel
import com.saulodev.melichallenge.domain.models.NeighborhoodModel
import com.saulodev.melichallenge.domain.models.PagingModel
import com.saulodev.melichallenge.domain.models.SellerContactModel
import com.saulodev.melichallenge.domain.models.SellerModel
import com.saulodev.melichallenge.domain.models.ShippingModel

fun ItemsResponse.toItemsResponseModel(): ItemsResponseModel {
    return ItemsResponseModel(
        countryDefaultTimeZone = this.countryDefaultTimeZone,
        pagingModel = this.paging?.toPagingModel(),
        query = this.query,
        items = this.results?.map { it.toItemModel() } ?: emptyList(),
        siteId = this.siteId
    )
}

fun Paging.toPagingModel(): PagingModel {
    return PagingModel(
        total = this.total,
        offset = this.offset,
        limit = this.limit,
        primaryResults = this.primaryResults,
    )
}

fun Result.toItemModel(): ItemModel {
    return ItemModel(
        acceptsMercadopago = this.acceptsMercadopago,
        attributeModels = this.attributes?.map { it.toAttributeModel() },
        availableQuantity = this.availableQuantity,
        buyingMode = this.buyingMode,
        catalogListing = this.catalogListing,
        categoryId = this.categoryId,
        condition = this.condition,
        currencyId = this.currencyId,
        domainId = this.domainId,
        id = this.id,
        listingTypeId = this.listingTypeId,
        locationModel = this.location?.toLocationModel(),
        orderBackend = this.orderBackend,
        permalink = this.permalink,
        price = this.price,
        sellerModel = this.seller?.toSellerModel(),
        sellerContactModel = this.sellerContact?.toSellerContactModel(),
        shippingModel = this.shipping?.toShippingModel(),
        siteId = this.siteId,
        stopTime = this.stopTime,
        thumbnail = this.thumbnail,
        thumbnailId = this.thumbnailId,
        title = this.title,
        useThumbnailId = this.useThumbnailId,
    )
}

fun Attribute.toAttributeModel(): AttributeModel {
    return AttributeModel(
        id = this.id,
        name = this.name,
        valueId = this.valueId,
        valueName = this.valueName,
        valueType = this.valueType,
        attributeGroupName = this.attributeGroupName,
        attributeGroupId = this.attributeGroupId,
        source = this.source
    )
}

fun Location.toLocationModel(): LocationModel {
    return LocationModel(
        addressLine = this.addressLine,
        cityModel = this.city.toCityModel(),
        countryModel = this.country.toCountryModel(),
        latitude = this.latitude,
        longitude = this.longitude,
        zipCode = this.zipCode,
        neighborhood = this.neighborhood.toNeighborhoodModel()
    )
}

fun City.toCityModel(): CityModel {
    return CityModel(
        id = this.id,
        name = this.name
    )
}

fun Country.toCountryModel(): CountryModel {
    return CountryModel(
        id = this.id,
        name = this.name
    )
}

fun Neighborhood.toNeighborhoodModel(): NeighborhoodModel {
    return NeighborhoodModel(
        id = this.id,
        name = this.name
    )
}

fun Seller.toSellerModel(): SellerModel {
    return SellerModel(
        id = this.id,
        nickname = this.nickname
    )
}

fun SellerContact.toSellerContactModel(): SellerContactModel {
    return SellerContactModel(
        areaCode = this.areaCode,
        areaCode2 = this.areaCode2,
        contact = this.contact,
        email = this.email,
        otherInfo = this.otherInfo,
        phone = this.phone,
        phone2 = this.phone2,
        webpage = this.webpage
    )
}

fun Shipping.toShippingModel(): ShippingModel {
    return ShippingModel(
        freeShipping = this.freeShipping,
        logisticType = this.logisticType,
        mode = this.mode,
        shippingScore = this.shippingScore,
        storePickUp = this.storePickUp,
    )
}