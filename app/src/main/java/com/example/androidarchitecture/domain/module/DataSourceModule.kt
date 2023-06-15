package com.example.androidarchitecture.domain.module

import com.example.androidarchitecture.data.local.ISharedPreference
import com.example.androidarchitecture.data.local.SharedPreferenceImpl
import com.example.androidarchitecture.data.remote.IRemoteService
import com.example.androidarchitecture.data.remote.RemoteService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface DataSourceModule {

    @Binds
    fun provideSharedPreferences(dataSource: SharedPreferenceImpl): ISharedPreference

    @Binds
    fun provideStorekeeperRemoteDataSource(dataSource: RemoteService): IRemoteService

}