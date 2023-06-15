package com.example.androidarchitecture.data.remote

import com.example.androidarchitecture.R
import com.example.androidarchitecture.commmon.CustomException
import com.example.androidarchitecture.commmon.Result
import com.example.androidarchitecture.data.remote.model.ApiPost
import com.example.androidarchitecture.data.remote.model.BaseApiErrorModel
import com.example.androidarchitecture.data.remote.model.BaseApiModel
import com.example.androidarchitecture.data.remote.request.UserRequest
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class RemoteService @Inject constructor(
    val apiService: ApiService
) : IRemoteService {

    private suspend fun <T : Any> apiRequest(apiFunc: suspend () -> Response<T?>) : Result<T?> =
        try {
            parseNetworkData(apiFunc.invoke())
        } catch (exception: Exception) {
            Result.Error(
                exception = if (exception is UnknownHostException || exception is ConnectException) {
                    CustomException(
                        messageResId = R.string.please_check_internet_connection
                    )
                } else {
                    exception
                }
            )
        }

    private fun <T : Any?> parseNetworkData(response: Response<T>) : Result<T?>{
        return if(response.isSuccessful){
            Result.Success(response.body())
        } else {
            if (response.code() == 401) {
                val errorBody = response.errorBody()?.string()
                val errorResponse = Gson().fromJson(
                    errorBody,
                    BaseApiModel::class.java
                )
                throw RuntimeException(errorResponse.statusMessage ?: "")
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = Gson().fromJson<List<BaseApiErrorModel>>(
                    errorBody,
                    object : TypeToken<List<BaseApiErrorModel>>() {}.type
                )
                throw RuntimeException(errorResponse.firstOrNull()?.errorMessage ?: "")
            }
        }
    }

    override suspend fun getUserList(): Result<List<UserRequest>> {
        return when(val result = apiRequest {
            apiService.getUserList(UserRequest("user","123456789"))
        }){
            is Result.Success ->{
                Result.Success(result.data ?: emptyList())
            }

            else -> {
                Result.Error((result as Result.Error).exception)
            }
        }
    }

    override suspend fun getPostList(): Result<List<ApiPost>> {
        return when(val result = apiRequest {
            apiService.getPostList()
        }){
            is Result.Success -> {
                Result.Success(result.data ?: emptyList())
            }
            else -> {
                Result.Error((result as Result.Error).exception)
            }
        }
    }
}