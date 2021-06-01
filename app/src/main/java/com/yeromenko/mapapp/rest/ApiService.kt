package com.yeromenko.mapapp.rest

import com.yeromenko.mapapp.model.Place
import com.yeromenko.mapapp.model.RootPlaces
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("kyiv/places")
    suspend fun getPlaces(): RootPlaces
}