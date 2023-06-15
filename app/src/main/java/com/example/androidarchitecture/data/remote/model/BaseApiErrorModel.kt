package com.example.androidarchitecture.data.remote.model

import com.google.gson.annotations.SerializedName

open class BaseApiErrorModel(
    @SerializedName("errorCode")
    val errorCode: String? = null,

    @SerializedName("errorMessage")
    val errorMessage: String? = null,
)