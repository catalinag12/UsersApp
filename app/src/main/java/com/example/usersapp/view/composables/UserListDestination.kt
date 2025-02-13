package com.example.usersapp.view.composables

import android.app.Activity
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.usersapp.presentation.UserPresentation
import com.example.usersapp.presentation.viewModel.UserViewModel
import com.example.usersapp.presentation.viewModel.ViewState
import kotlin.random.Random

@Composable
fun UserListDestination(
    factory: ViewModelProvider.Factory,
    vm: UserViewModel = viewModel(factory = factory)
) {
    val state by vm.viewState.collectAsState()
    val listState = rememberLazyListState()

    val getNextPage: Boolean by remember {
        derivedStateOf {
            if (state is ViewState.Loaded) {
                val currentUsersSize = (state as ViewState.Loaded).users.size
                val lastUserIndex = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
                    ?: return@derivedStateOf false
                return@derivedStateOf lastUserIndex >= currentUsersSize - 3
            } else return@derivedStateOf false
        }
    }

    LaunchedEffect(getNextPage) {
        if (getNextPage) {
            vm.getNextPage()
        }
    }

    when (state) {
        is ViewState.Error -> {
            ErrorScreen((state as ViewState.Error).error)
        }
        is ViewState.Loaded -> {
            UserListScreen(
                users = (state as ViewState.Loaded).users,
                listState = listState,
            )
        }
        ViewState.Loading -> {
            LoadingItem()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(
    users: List<UserPresentation>,
    listState: LazyListState,
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
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                containerColor = Color(0xFFBD3F34),
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "edit"
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            state = listState
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