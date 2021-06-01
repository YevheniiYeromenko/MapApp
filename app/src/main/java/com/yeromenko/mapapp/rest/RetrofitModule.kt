package com.yeromenko.mapapp.rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitModule {
    fun apiService(): ApiService{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://2fjd9l3x1l.api.quickmocker.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)
    }

}