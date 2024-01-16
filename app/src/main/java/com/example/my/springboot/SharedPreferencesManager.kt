package com.example.my.springboot

import android.content.Context

class SharedPreferencesManager(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("MyApp", Context.MODE_PRIVATE)

    fun saveUserDetails(ad: String, soyad: String) {
        with(sharedPreferences.edit()) {
            putString("ad", ad)
            putString("soyad", soyad)
            apply()
        }
    }

    fun getUserDetails(): Pair<String?, String?> {
        val ad = sharedPreferences.getString("ad", null)
        val soyad = sharedPreferences.getString("soyad", null)
        return Pair(ad, soyad)
    }
}
