package com.saulodev.melichallenge.domain.models


import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class AttributeModel(
    val attributeGroupId: String?,
    val attributeGroupName: String?,
    val id: String?,
    val name: String?,
    val source: Long?,
    val valueId: String?,
    val valueName: String?,
    val valueType: String?,
) : Parcelable