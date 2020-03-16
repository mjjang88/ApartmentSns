package com.mjjang.apartmentsns.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApartmentDBRetrofit {
    fun getService(): RetrofitService = retrofit.create(RetrofitService::class.java)

    private val retrofit =
        Retrofit.Builder()
            .baseUrl("https://apartment-db.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}