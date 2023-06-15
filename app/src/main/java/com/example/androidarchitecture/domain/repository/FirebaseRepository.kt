package com.example.androidarchitecture.domain.repository

import android.util.Log
import com.example.androidarchitecture.domain.module.IoDispatcher
import com.example.androidarchitecture.presentation.adapter.MyItem
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FirebaseRepository @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : IFirebaseRepository{
    private val firestore = FirebaseFirestore.getInstance()

    override suspend fun fetchData(pageSize: Long, lastItemId: String?): List<MyItem> = withContext(dispatcher) {
        val query = firestore.collection("data")
            .orderBy("name")
            .startAfter(lastItemId)
            .limit(pageSize)

        try {
            val snapshot = query.get().await()
            Log.i("document id",snapshot.documents.size.toString())
            snapshot.documents.mapNotNull { document ->
                val id = document.id
                val name = document.getString("name")
                if (name != null) MyItem(id,name) else null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}