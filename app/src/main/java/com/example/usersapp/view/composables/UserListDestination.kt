package com.example.usersapp.view.composables

import android.app.Activity
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.usersapp.presentation.UserPresentation
import com.example.usersapp.presentation.UserViewModel
import com.example.usersapp.presentation.ViewState
import kotlin.random.Random

@Composable
fun UserListDestination(
    factory: ViewModelProvider.Factory,
    vm: UserViewModel = viewModel(factory = factory)
) {

    val state by vm.viewState.collectAsState()

    when (state) {
        is ViewState.Error -> {
            ErrorScreen((state as ViewState.Error).error)
        }
        is ViewState.Loaded -> {
            UserListScreen(users = (state as ViewState.Loaded).users)
        }
        ViewState.Loading -> {
            LoadingItem()
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(
    users: List<UserPresentation>
) {

    val activity = LocalView.current.context as Activity
    val color = Color(0xFFBB2802)
    activity.window.statusBarColor = color.toArgb()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Users",
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFFBD3F34),
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "burger menu"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "search"
                        )
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding
        ) {
            items(users) { user ->
                UserListItem(
                    imageUrl = user.picture,
                    fullName = user.fullName,
                    ageAndCountry = user.details,
                    timestamp = user.formattedTimestamp,
                    showDetailsIcon = Random.nextBoolean()
                )
            }
        }
    }
}