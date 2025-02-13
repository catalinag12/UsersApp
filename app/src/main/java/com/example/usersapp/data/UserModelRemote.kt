package com.example.usersapp.data

import com.google.gson.annotations.SerializedName

data class UserResults(
    @SerializedName("results")
    val userResults: List<UserRemote>
)

data class Name(
    @SerializedName("first")
    val firstName: String,
    @SerializedName("last")
    val lastName: String,
)

data class Dob(
    @SerializedName("age")
    val age: Int,
)

data class Location(
    @SerializedName("country")
    val countryName: String,
)

data class Picture(
    @SerializedName("medium")
    val image: String,
)

data class UserRemote(
    @SerializedName("name")
    val name: Name,
    @SerializedName("dob")
    val dob: Dob,
    @SerializedName("location")
    val location: Location,
    @SerializedName("picture")
    val picture: Picture
)