package com.example.usersapp.data

import retrofit2.http.GET
import retrofit2.http.Query

interface UserRemoteSource {
    @GET("api/")
    suspend fun getUsers(
        @Query("page")
        page: Int = 0,
        @Query("results")
        results: Int = 20,
        @Query("seed")
        seed: String = "abc"
    ): UserResults
}