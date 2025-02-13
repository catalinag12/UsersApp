package com.example.usersapp.utils

interface NoArgsUseCase<T> {
    fun execute(): T
}