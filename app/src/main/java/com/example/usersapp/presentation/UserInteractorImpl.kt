package com.example.usersapp.presentation

import com.example.usersapp.domain.GetUsersUseCase
import com.example.usersapp.domain.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


interface UserInteractor {
    fun getUsers(): Flow<List<UserPresentation>>
}

class UserInteractorImpl @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : UserInteractor {
    override fun getUsers(): Flow<List<UserPresentation>> =
        getUsersUseCase.execute().map { it.map(User::toPresentation) }

}

