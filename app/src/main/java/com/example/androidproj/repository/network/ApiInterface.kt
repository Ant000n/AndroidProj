package com.example.androidproj.repository.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiInterface {

    @GET("todos/{num}")
    fun getNetworkData(@Path("num") num: Int): Call<Entity>


    @GET("public-api/users")
    fun getNetworkData2(
        @Query("_format") format: String,
        @Query("access-token") accessToken: String
    ): Call<ResponseBody>

}


