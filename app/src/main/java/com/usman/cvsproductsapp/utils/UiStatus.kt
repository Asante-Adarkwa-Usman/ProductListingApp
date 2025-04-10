package com.usman.cvsproductsapp.utils

sealed class UiStatus<out T> {
    data object Loading: UiStatus<Nothing>()
    data class Success<T>(val data: T): UiStatus<T>()
    data class Error(val message:String): UiStatus<Nothing>()
}