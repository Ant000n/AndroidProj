package com.example.androidproj.repository.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {

    @GET("todos/{num}")
    fun getNetworkData(@Path("num") num: Int): Call<Entity>


    @GET("public-api/users")
    fun getNetworkData2(
        @Query("_format") format: String,
        @Query("access-token") accessToken: String,
        @Query("email") email: String
    ): Call<UserListResponse>

    @POST("public-api/users")
    fun createNewUser(
        @Header("Authorization VBoALh0IUiCXleTsvpp8MqP6b2nquhIBCJRq") token: String,
        @Body newUser: NewUserRequest
    ): Call<ResponseBody>

    @DELETE("public-api/users/{id}")
    fun deleteUser(
        @Path("id") userId: String
    )

    fun updateUser(
        @Header("Authorization VBoALh0IUiCXleTsvpp8MqP6b2nquhIBCJRq") token: String,
        @Body user: NewUserRequest
    ): Call<ResponseBody>


//    @GET("public-api/users")
//    fun getUSerList(
//        @Query("")
//    )

}


