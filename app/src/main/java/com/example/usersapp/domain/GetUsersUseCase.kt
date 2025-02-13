package com.example.usersapp.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UserRepository
) {
    fun execute(page: Int): Flow<List<User>> {
        return repository.getUsers(page)
    }
}