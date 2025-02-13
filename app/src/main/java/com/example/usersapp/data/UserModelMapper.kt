package com.example.usersapp.data

import com.example.usersapp.domain.User
import com.example.usersapp.utils.getCountryCode
import java.time.Instant

internal fun UserRemote.toDomain(): User = User(
    fullName = "${name.firstName} ${name.lastName}",
    age = dob.age,
    countryCode = getCountryCode(countryName = location.countryName) ?: "UNKNOWN",
    picture = picture.image,
    timestamp = Instant.now()
)

