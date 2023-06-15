package com.example.androidarchitecture.domain.module

import com.example.androidarchitecture.domain.repository.FirebaseRepository
import com.example.androidarchitecture.domain.repository.IFirebaseRepository
import com.example.androidarchitecture.domain.repository.IUserRepository
import com.example.androidarchitecture.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Binds
    fun provideUserRepository(userRepository : UserRepository) : IUserRepository

    @Binds
    fun provideFirebaseRepository(firebaseRepository: FirebaseRepository) : IFirebaseRepository
}