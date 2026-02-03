package com.nak.learningandroid

sealed class State {
    object Idle: State()
    object Loading: State()
    object Success: State()
    object Error: State()
}

//Singleton -> Enums

enum class ApiState {
    LOADING,
    SUCCESS,
    ERROR
}

sealed interface Shape
sealed interface Route


sealed interface Api: Shape, Route {
    val state: ApiState
}