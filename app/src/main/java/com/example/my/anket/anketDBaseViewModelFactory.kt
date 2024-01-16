package com.example.my.anket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class AnketDBaseViewModelFactory(private val anketApıService: AnketApiService) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AnketDBaseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AnketDBaseViewModel(anketApıService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
