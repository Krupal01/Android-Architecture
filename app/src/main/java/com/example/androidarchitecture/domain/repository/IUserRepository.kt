package com.example.androidarchitecture.domain.repository

import com.example.androidarchitecture.commmon.Result
import com.example.androidarchitecture.data.remote.model.ApiPost

interface IUserRepository {

    suspend fun getPostList() : Result<List<ApiPost>>
}