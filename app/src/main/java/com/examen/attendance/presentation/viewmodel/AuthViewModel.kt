package com.examen.attendance.presentation.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examen.attendance.data.remote.AuthUser
import com.examen.attendance.data.utils.ResultState
import com.examen.attendance.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository) :ViewModel(){
    private val _isDialog = MutableLiveData<Boolean>(false)
    val isDialog: LiveData<Boolean> = _isDialog

    private val _userData = MutableLiveData<String>()
    val userData: LiveData<String> = _userData

   fun createUser(authUser: AuthUser) {
     viewModelScope.launch(IO){
         authRepository.createUser(authUser).collect { result ->
             when (result) {
                 is ResultState.Success -> {
                     _userData.value = result.data
                     _isDialog.value = false
                 }
                 is ResultState.Failure -> {
                     _userData.value = result.msg.toString()
                     _isDialog.value = false
                 }
                 ResultState.Loading -> {
                     _isDialog.value = true
                 }
             }
         }
     }
   }

  //  fun createUser(authUser: AuthUser) = authRepository.createUser(authUser)

    fun loginUser(authUser: AuthUser) = authRepository.loginUser(authUser)



}