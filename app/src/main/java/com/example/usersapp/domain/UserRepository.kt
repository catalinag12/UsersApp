package com.example.usersapp.domain

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(page: Int): Flow<List<User>>
}