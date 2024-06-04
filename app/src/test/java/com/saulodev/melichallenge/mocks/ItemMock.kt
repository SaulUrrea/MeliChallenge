package com.saulodev.melichallenge.mocks

import com.saulodev.melichallenge.data.dto.ItemsResponse
import com.saulodev.melichallenge.data.dto.Paging
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

class ItemMock {
    companion object {

        fun getItemResponseDtoMock(): ItemsResponse {
            return ItemsResponse(
                countryDefaultTimeZone = "UTC",
                paging = Paging(total = 100, offset = 0, limit = 0, primaryResults = 0),
                query = "test query",
                results = listOf(),
                siteId = "MLA"
            )
        }

        fun getItemResponseMock(): ItemsResponseModel {
            return ItemsResponseModel(
                countryDefaultTimeZone = "UTC",
                pagingModel = PagingModel(total = 100, offset = 0, limit = 0, primaryResults = 0),
                query = "test query",
                items = listOf(
                    getItemMock(),
                ),
                siteId = "MLA"
            )
        }
        fun getItemResponseWithEmptyLisyMock(): ItemsResponseModel {
            return ItemsResponseModel(
                countryDefaultTimeZone = "UTC",
                pagingModel = PagingModel(total = 100, offset = 0, limit = 0, primaryResults = 0),
                query = "test query",
                items = listOf(),
                siteId = "MLA"
            )
        }

        fun getItemMock(): ItemModel {
            return ItemModel(
                acceptsMercadopago = true,
                attributeModels = listOf(
                    getAttributeModel(),
                    getAttributeModel(),
                    getAttributeModel()
                ),
                availableQuantity = 10,
                buyingMode = "buy_it_now",
                catalogListing = false,
                categoryId = "MLA1234",
                condition = "new",
                currencyId = "ARS",
                domainId = "MLA-CELLPHONES",
                id = "MLA987654321",
                listingTypeId = "gold_special",
                locationModel = getLocationModel(),
                orderBackend = 1,
                permalink = "https://www.mercadolibre.com.ar/item/MLA987654321",
                price = 1000.0,
                sellerModel = getSellerModel(),
                sellerContactModel = getSellerContactModel(),
                shippingModel = getShippingModel(),
                siteId = "MLA",
                stopTime = "2024-03-10T04:00:00.000Z",
                thumbnail = "https://http2.mlstatic.com/D_645554-MLA76554275740_052024-I.jpg",
                thumbnailId = "987654-MLA456789012_012023",
                title = "Producto Ejemplo",
                useThumbnailId = true,
            )
        }

        fun getShippingModel(): ShippingModel? {
            return ShippingModel(
                freeShipping = true,
                logisticType = "cross_docking",
                mode = "me2",
                shippingScore = 10,
                storePickUp = true
            )
        }

        fun getAttributeModel(): AttributeModel {
            return AttributeModel(
                attributeGroupId = "GROUP_ID_1",
                attributeGroupName = "GROUP_NAME_1",
                id = "ATTRIBUTE_ID_1",
                name = "Attribute Name 1",
                source = 12345L,
                valueId = "VALUE_ID_1",
                valueName = "Value Name 1",
                valueType = "string"
            )
        }

        fun getLocationModel(): LocationModel {
            return LocationModel(
                addressLine = "Test Address",
                cityModel = CityModel(id = "CITY_ID", name = "Test City"),
                countryModel = CountryModel(id = "COUNTRY_ID", name = "Test Country"),
                latitude = 34.0522,
                longitude = -118.2437,
                zipCode = "12345",
                neighborhood = NeighborhoodModel(id = "NEIGHBORHOOD_ID", name = "Test Neighborhood")
            )
        }

        fun getSellerModel(): SellerModel {
            return SellerModel(
                id = 12345,
                nickname = "Test Seller"
            )
        }

        fun getSellerContactModel(): SellerContactModel {
            return SellerContactModel(
                areaCode = "123",
                areaCode2 = "456",
                contact = "Test Contact",
                email = "test@example.com",
                otherInfo = "Other Info",
                phone = "123-456-7890",
                phone2 = "987-654-3210",
                webpage = "https://www.example.com"
            )
        }
    }
}

