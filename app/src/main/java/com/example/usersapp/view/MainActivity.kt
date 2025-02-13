package com.example.usersapp.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.usersapp.presentation.UserViewModelFactory
import com.example.usersapp.ui.theme.UsersAppTheme
import com.example.usersapp.view.DI.DaggerUserComponent
import com.example.usersapp.view.composables.UserListDestination
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var factory: UserViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerUserComponent.create().inject(this)

        setContent {
            UsersAppTheme {
                UserListDestination(
                    factory = factory
                )
            }
        }
    }
}