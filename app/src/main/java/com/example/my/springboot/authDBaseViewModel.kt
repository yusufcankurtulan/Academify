package com.example.my.springboot

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception

class AuthDBaseViewModel(
    private val authApiService: AuthApiService,
    private val sharedPreferencesManager: SharedPreferencesManager
) : ViewModel() {

    suspend fun login(users: Users, onLoginResult: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = authApiService.login(users)
                val loginSuccessful = response["success"] as? Boolean ?: false
                val message = response["message"] as? String ?: ""
                if (loginSuccessful) {
                    val ad = response["ad"] as? String ?: ""
                    val soyad = response["soyad"] as? String ?: ""
                    sharedPreferencesManager.saveUserDetails(ad, soyad)
                }
                onLoginResult(loginSuccessful, message)
            } catch (e: Exception) {
                onLoginResult(false, "Network error")
            }
        }
    }

    suspend fun register(users : Users, onRegisterResult: (Boolean,String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = authApiService.register(users)
                val registerSuccessful = response["success"] as? Boolean ?: false
                val message = response["message"] as? String ?: ""

                onRegisterResult(registerSuccessful, message)
            } catch (e: Exception) {
                onRegisterResult(false, "Network error")
            }
        }
    }
}
