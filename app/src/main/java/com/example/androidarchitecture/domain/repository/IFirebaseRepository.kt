package com.example.androidarchitecture.domain.repository

import com.example.androidarchitecture.presentation.adapter.MyItem

interface IFirebaseRepository{
    suspend fun fetchData(pageSize: Long, lastItemId: String? = null): List<MyItem>
}