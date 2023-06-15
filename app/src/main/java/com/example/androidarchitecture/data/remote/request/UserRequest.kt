package com.example.androidarchitecture.data.remote.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("number")
    val number: String
) : Serializable
