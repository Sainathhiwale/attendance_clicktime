package com.examen.attendance.domain.usecase

import com.examen.attendance.data.remote.AuthUser
import com.examen.attendance.data.utils.ResultState
import com.examen.attendance.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserLoginUseCase (private val authRepository: AuthRepository) {

     fun  loginUser(authUser: AuthUser): Flow<ResultState<String>> {
        return flow {
             emit(ResultState.Loading)
            try {
                val result = authRepository.loginUser(authUser)
                emit(ResultState.Success(result.toString()))
            }catch (e:Exception){
                emit(ResultState.Failure(e))
            }
        }

    }


}