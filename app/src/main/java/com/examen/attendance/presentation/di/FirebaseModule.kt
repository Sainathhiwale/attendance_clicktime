package com.examen.attendance.presentation.di

import com.examen.attendance.data.datasource.AuthRepositoryImpl
import com.examen.attendance.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {

    @Singleton
    @Provides
    fun providesFirebaseAuth(): FirebaseAuth = Firebase.auth

    @Singleton
    @Provides
    fun providesFirebaseAuthRepository(): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth = Firebase.auth)
    }

}