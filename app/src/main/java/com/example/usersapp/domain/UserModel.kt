package com.example.usersapp.domain

import java.time.Instant

data class User(
    val fullName: String,
    val age: Int,
    val countryCode: String,
    val picture: String,
    val timestamp: Instant
)