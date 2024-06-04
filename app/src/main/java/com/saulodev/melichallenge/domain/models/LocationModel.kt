package com.saulodev.melichallenge.domain.models


import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class LocationModel(
    val addressLine: String?,
    val cityModel: CityModel?,
    val countryModel: CountryModel?,
    val latitude: Double?,
    val longitude: Double?,
    val zipCode: String?,
    val neighborhood: NeighborhoodModel?
) : Parcelable