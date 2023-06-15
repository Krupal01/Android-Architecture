package com.example.androidarchitecture.data.remote

import com.example.androidarchitecture.data.remote.model.ApiPost
import com.example.androidarchitecture.data.remote.request.UserRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface ApiService {

    suspend fun getUserList(@Body request : UserRequest) : Response<List<UserRequest>?>

    @GET("posts")
    suspend fun getPostList() : Response<List<ApiPost>?>
}