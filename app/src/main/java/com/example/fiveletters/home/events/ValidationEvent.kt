package com.example.fiveletters.home.events

sealed class ValidationEvent {
    object Success : ValidationEvent()
    object Error : ValidationEvent()
}