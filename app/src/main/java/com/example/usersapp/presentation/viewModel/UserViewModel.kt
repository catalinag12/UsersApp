package com.example.usersapp.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usersapp.presentation.UserInteractor
import com.example.usersapp.presentation.UserPresentation
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val interactor: UserInteractor
) : ViewModel() {
    private val _viewState: MutableStateFlow<ViewState> = MutableStateFlow(ViewState.Loading)
    val viewState = _viewState.asStateFlow()

    private val fetchedUserPages = mutableListOf<List<UserPresentation>>()

    init {
        getUsers()
    }
    private fun getUsers() = viewModelScope.launch {
        _viewState.tryEmit(ViewState.Loading)

        if (fetchedUserPages.isNotEmpty()) return@launch

        interactor.getUsers(page = 0)
            .catch {
                _viewState.emit(ViewState.Error(it.toString()))
            }
            .collect { userPage ->
                fetchedUserPages.clear()
                fetchedUserPages.add(userPage)

                _viewState.update {
                    ViewState.Loaded(users = userPage)
                }
            }
    }

    fun getNextPage() = viewModelScope.launch {
        val nextPageIndex = fetchedUserPages.size + 1

        if (nextPageIndex > 3) return@launch

        interactor.getUsers(page = nextPageIndex)
            .catch {
                _viewState.emit(ViewState.Error(it.toString()))
            }
            .collect { userPage ->
                if (userPage.isNotEmpty()) {
                    fetchedUserPages.add(userPage)

                    _viewState.update { currentState ->
                        val currentUsers = (currentState as? ViewState.Loaded)?.users ?: emptyList()
                        ViewState.Loaded(users = currentUsers + userPage)
                    }
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