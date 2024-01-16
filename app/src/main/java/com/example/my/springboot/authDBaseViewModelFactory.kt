package com.example.my.springboot

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AuthDBaseViewModelFactory(
    private val authApiService: AuthApiService,
    private val sharedPreferencesManager: SharedPreferencesManager
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthDBaseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AuthDBaseViewModel(authApiService, sharedPreferencesManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
