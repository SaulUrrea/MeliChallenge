package com.saulodev.melichallenge.data.dto


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Attribute(
    @SerializedName("attribute_group_id")
    val attributeGroupId: String,
    @SerializedName("attribute_group_name")
    val attributeGroupName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("source")
    val source: Long,
    @SerializedName("value_id")
    val valueId: String,
    @SerializedName("value_name")
    val valueName: String,
    @SerializedName("value_type")
    val valueType: String,
)