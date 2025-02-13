package com.example.usersapp.data

import com.example.usersapp.domain.User
import com.example.usersapp.domain.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: UserRemoteSource
) : UserRepository {
    override fun getUsers(page: Int): Flow<List<User>> = flow {
        val users = api.getUsers(page = page)
        val mappedUsers = users.userResults.map { it.toDomain() }
        emit(mappedUsers)
    }
}