package com.examen.attendance.data.datasource

import android.util.Log
import com.examen.attendance.data.remote.AuthUser
import com.examen.attendance.data.utils.ResultState
import com.examen.attendance.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth): AuthRepository {

    override  fun createUser(auth: AuthUser): Flow<ResultState<String>> = callbackFlow{
        trySend(ResultState.Loading)

        firebaseAuth.createUserWithEmailAndPassword(
            auth.email!!,
            auth.password!!
        ).addOnCompleteListener {
            if(it.isSuccessful){
                trySend(ResultState.Success("User created successfully"))
                Log.d("main", "current user id: ${firebaseAuth.currentUser?.uid}")
            }
        }.addOnFailureListener {
            trySend(ResultState.Failure(it))
        }

        awaitClose {
            close()
        }
    }

    override  fun loginUser(auth: AuthUser): Flow<ResultState<String>> = callbackFlow{
        trySend(ResultState.Loading)

        firebaseAuth.signInWithEmailAndPassword(
            auth.email!!,
            auth.password!!
        ).addOnSuccessListener {
            trySend(ResultState.Success("login Successfully"))
            Log.d("main", "current user id: ${firebaseAuth.currentUser?.uid}")
        }.addOnFailureListener {
            trySend(ResultState.Failure(it))
        }
        awaitClose {
            close()
        }
    }
}