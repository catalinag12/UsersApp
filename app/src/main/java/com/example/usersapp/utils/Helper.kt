package com.example.usersapp.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

internal fun getCountryCode(countryName: String) =
    Locale.getISOCountries().find {
        Locale("", it).displayCountry == countryName
    }

internal fun formatTime(): String {
    val formatter = DateTimeFormatter.ofPattern("hh:mm")
    return LocalDateTime.now().format(formatter)
}