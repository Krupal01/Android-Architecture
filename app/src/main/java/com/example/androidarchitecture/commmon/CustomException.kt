package com.example.androidarchitecture.commmon

data class CustomException(
    val messageResId : Int = 0
) : Exception("Error")

data class ConnectionException(
    override val message : String = "Connection Error",
    val messageResId: Int = 0
) : Exception(message)