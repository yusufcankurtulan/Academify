package com.example.my.anket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception

class AnketDBaseViewModel(private val anketApiService: AnketApiService): ViewModel(){

    suspend fun yukleAnketVerileri(anketVerileri: AnketVerileri, onYuklemeResult: (Boolean, String) -> Unit) {
        viewModelScope.launch{
            try {
                val response = anketApiService.yukleAnketVerileri(anketVerileri)
                val success = response["success"] as? Boolean ?: false
                val message = response["message"] as? String ?: "Yükleme başarısız."

                onYuklemeResult(success, message)
            } catch (e: Exception) {
                onYuklemeResult(false, "Network hatası: ${e.message}")
            }
        }
    }

    suspend fun getAnketVerileri(onGetResult: (List<AnketVerileri>?) -> Unit) {
        viewModelScope.launch {
            try {
                val anketVerileri = anketApiService.getAnketVerileri()
                onGetResult(anketVerileri)
            } catch (e: Exception) {
                onGetResult(null)
            }
        }
    }
}
