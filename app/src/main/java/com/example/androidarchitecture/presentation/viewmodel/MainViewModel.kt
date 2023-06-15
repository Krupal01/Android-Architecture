package com.example.androidarchitecture.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidarchitecture.commmon.Result
import com.example.androidarchitecture.data.remote.model.ApiPost
import com.example.androidarchitecture.domain.repository.IUserRepository
import com.example.androidarchitecture.domain.repository.UserRepository
import com.example.androidarchitecture.model.UiDialogMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: IUserRepository
) : ViewModel() {

    private val _dialogMessage = MutableLiveData<UiDialogMessage>()
    val dialogMessage: LiveData<UiDialogMessage> get() = _dialogMessage

    private val _postList = MutableLiveData<List<ApiPost>>()
    val postList: LiveData<List<ApiPost>> get() = _postList

    fun getPost(){
        viewModelScope.launch {
            when(val result = userRepository.getPostList()){
                is Result.Success ->{
                    _postList.postValue(result.data ?: emptyList())
                }else ->{
                    _dialogMessage.value = UiDialogMessage(message = (result as Result.Error).exception?.message ?: "Error")
                }
            }
        }
    }
}