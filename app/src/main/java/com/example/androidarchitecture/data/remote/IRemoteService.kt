package com.example.androidarchitecture.data.remote

import com.example.androidarchitecture.commmon.Result
import com.example.androidarchitecture.data.remote.model.ApiPost
import com.example.androidarchitecture.data.remote.request.UserRequest

interface IRemoteService {

    suspend fun getUserList():Result<List<UserRequest>>

    suspend fun getPostList() : Result<List<ApiPost>>
}