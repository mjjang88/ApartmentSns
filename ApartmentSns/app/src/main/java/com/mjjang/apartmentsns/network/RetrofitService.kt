package com.mjjang.apartmentsns.network

import com.mjjang.apartmentsns.data.Apartment
import com.mjjang.apartmentsns.data.Post
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {

    @GET("/apartment")
    fun requerstApartment(
    ): Call<List<Apartment>>

    @GET("/post")
    fun requestPost(
    ): Call<List<Post>>
}