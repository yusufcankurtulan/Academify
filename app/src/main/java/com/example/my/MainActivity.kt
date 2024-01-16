package com.example.my

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import com.example.my.springboot.AuthDBaseViewModel
import com.example.my.springboot.AuthDBaseViewModelFactory
import com.example.my.springboot.AuthRetrofitInstance
import com.example.my.springboot.SharedPreferencesManager
import com.example.my.tabs.AnaEkran

class MainActivity : ComponentActivity() {

    // AuthDBaseViewModelFactory nesnesini burada doğru bir şekilde oluşturun
    private val authDBaseViewModelFactory by lazy {
        val sharedPreferencesManager = SharedPreferencesManager(this)
        AuthDBaseViewModelFactory(AuthRetrofitInstance.authApi, sharedPreferencesManager)
    }

    // ViewModel'i doğru bir şekilde başlatın
    private val authDBaseViewModel: AuthDBaseViewModel by viewModels { authDBaseViewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                AnaEkran(authDBaseViewModel = authDBaseViewModel)
            }
        }
    }
}

