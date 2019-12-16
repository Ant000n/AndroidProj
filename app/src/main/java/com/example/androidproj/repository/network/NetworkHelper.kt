package com.example.androidproj.repository.network


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkHelper {

    fun getApi(): ApiInterface {

        val retrofit = Retrofit.Builder()
            //.baseUrl("https://jsonplaceholder.typicode.com/")
            .baseUrl("https://gorest.co.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiInterface::class.java)
    }

}