package com.example.usersapp

import com.example.usersapp.presentation.UserInteractor
import com.example.usersapp.presentation.UserPresentation
import com.example.usersapp.presentation.viewModel.UserViewModel
import com.example.usersapp.presentation.viewModel.ViewState
import com.example.usersapp.utils.formatTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.test.resetMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class UserViewModelTest {
    private val interactor: UserInteractor = mock()
    private val testDispatcher = TestCoroutineDispatcher()

    private val underTest by lazy {
        UserViewModel(
            interactor = interactor
        )
    }

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when users are fetched, emit states`() = runTest {
        val usersMockList = listOf(
            UserPresentation("user 1", "image.html", "30 years from US", formatTime()),
            UserPresentation("user 2", "image.html", "35 years from US", formatTime()),
        )

        whenever(interactor.getUsers(0)).thenReturn(flowOf(usersMockList))

        val state = underTest.viewState.first()

        assertEquals(ViewState.Loaded(usersMockList), state)
    }

    @Test
    fun `when users call fail, emit error`() = runTest {

        whenever(interactor.getUsers(0)).thenReturn(flow { throw Exception() })

        val state = underTest.viewState.first()

        assert(state is ViewState.Error)
        assertEquals(ViewState.Error("error").error, "error")
    }
}