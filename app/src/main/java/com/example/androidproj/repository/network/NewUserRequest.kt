package com.example.androidproj.repository.network

data class NewUserRequest(
    val first_name: String,
    val last_name: String,
    val gender: String,
    val email: String,
    val status: String
)