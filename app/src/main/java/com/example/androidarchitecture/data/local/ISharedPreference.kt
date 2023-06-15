package com.example.androidarchitecture.data.local

interface ISharedPreference {

    suspend fun store(key: String , value: Any)

    suspend fun <T> get(key: String, default: T) : T

    suspend fun clear()
}