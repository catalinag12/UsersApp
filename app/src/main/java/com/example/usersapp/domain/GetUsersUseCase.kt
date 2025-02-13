package com.example.usersapp.domain

import com.example.usersapp.data.UserRemote
import com.example.usersapp.utils.NoArgsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UserRepository
) : NoArgsUseCase<Flow<List<User>>> {
    override fun execute(): Flow<List<User>> {
        return repository.getUsers()
    }
}