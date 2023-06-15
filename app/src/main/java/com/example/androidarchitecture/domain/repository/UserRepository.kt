package com.example.androidarchitecture.domain.repository

import com.example.androidarchitecture.commmon.Result
import com.example.androidarchitecture.data.local.ISharedPreference
import com.example.androidarchitecture.data.remote.IRemoteService
import com.example.androidarchitecture.data.remote.model.ApiPost
import com.example.androidarchitecture.domain.module.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepository @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val sharedPreference: ISharedPreference,
    private val remoteService: IRemoteService
): IUserRepository {

    override suspend fun getPostList(): Result<List<ApiPost>> = withContext(dispatcher) {
        remoteService.getPostList()
    }

}