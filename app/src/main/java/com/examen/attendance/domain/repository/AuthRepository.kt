package com.examen.attendance.domain.repository

import com.examen.attendance.data.remote.AuthUser
import com.examen.attendance.data.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun createUser(auth: AuthUser) : Flow<ResultState<String>>

    fun loginUser(auth: AuthUser) : Flow<ResultState<String>>

}