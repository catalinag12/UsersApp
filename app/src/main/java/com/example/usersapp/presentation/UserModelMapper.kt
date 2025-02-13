package com.example.usersapp.presentation

import com.example.usersapp.domain.User
import com.example.usersapp.utils.formatTime

internal fun User.toPresentation(): UserPresentation = UserPresentation(
    fullName = fullName,
    details = "$age years from $countryCode",
    picture = picture,
    formattedTimestamp = formatTime()
)