package com.example.androidarchitecture.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidarchitecture.domain.repository.IFirebaseRepository
import com.example.androidarchitecture.presentation.adapter.MyItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirebaseViewModel @Inject constructor(
    private val firebaseRepository: IFirebaseRepository
) : ViewModel(){

    private val pageSize = 5L
    private var lastItemId: String? = null
    private var isLoading = false
    var isLastPage = false
    private val _dataList = MutableLiveData<List<MyItem>>()
    val dataList: LiveData<List<MyItem>>
        get() = _dataList

    fun loadData() {

        if (isLoading || isLastPage) return

        isLoading = true
        viewModelScope.launch {
            val newData = firebaseRepository.fetchData(pageSize, lastItemId)

            lastItemId = newData.lastOrNull()?.name
            val currentData = _dataList.value.orEmpty()
            _dataList.value = currentData + newData
            isLoading = false

            if (newData.size < pageSize) {
                isLastPage = true
            }
        }
    }
}

