package com.example.usersapp.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.usersapp.presentation.UserInteractor
import com.example.usersapp.presentation.UserViewModel
import javax.inject.Inject


class UserViewModelFactory @Inject constructor(
    private val userInteractor: UserInteractor
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewModel(
            interactor = userInteractor
        )
                as T
    }
}