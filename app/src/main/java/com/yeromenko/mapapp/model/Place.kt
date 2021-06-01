package com.yeromenko.mapapp.model

import com.google.gson.annotations.SerializedName

class Place(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("lat") var lat: Double,
    @SerializedName("lng") var lng: Double
    ) {
}