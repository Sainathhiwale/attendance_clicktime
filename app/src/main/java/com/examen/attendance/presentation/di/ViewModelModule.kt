package com.examen.attendance.presentation.di

import com.examen.attendance.domain.repository.AuthRepository
import com.examen.attendance.presentation.viewmodel.AuthViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
 class ViewModelModule {

     @Singleton
     @Provides
     fun providesAuthViewModel (authRepository: AuthRepository):AuthViewModel{
         return AuthViewModel(authRepository)
     }

}

