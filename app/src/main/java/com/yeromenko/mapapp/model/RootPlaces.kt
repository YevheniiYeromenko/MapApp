package com.yeromenko.mapapp.model

import com.google.gson.annotations.SerializedName
class RootPlaces(@SerializedName("places") var places: MutableList<Place>) {
}