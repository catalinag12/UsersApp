package com.example.usersapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usersapp.data.UserRemote
import com.example.usersapp.domain.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val interactor: UserInteractor
) : ViewModel() {
    private val _viewState: MutableStateFlow<ViewState> = MutableStateFlow(ViewState.Loading)
    val viewState = _viewState.asStateFlow()

    init {
        getUsers()
    }

    private fun getUsers() {
        _viewState.tryEmit(ViewState.Loading)
        viewModelScope.launch {
            interactor.getUsers()
                .catch {
                    _viewState.emit(ViewState.Error(it.toString()))
                }
                .collect {
                    _viewState.emit(ViewState.Loaded(it))
                }
        }
    }
}

sealed interface ViewState {
    object Loading : ViewState
    data class Error(
        val error: String
    ) : ViewState

    data class Loaded(
        val users: List<UserPresentation>
    ) : ViewState
}