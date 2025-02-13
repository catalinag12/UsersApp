package com.example.usersapp.utils

import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.*

internal fun getCountryCode(countryName: String) =
    Locale.getISOCountries().find {
        Locale("", it).displayCountry == countryName
    }

internal fun Instant.formatTime(): String {
    val formatter = DateTimeFormatter.ofPattern("hh:mm", Locale.getDefault())
    return formatter.format(this)
}