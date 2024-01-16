package com.example.my.tabs

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class FavoriViewModel : ViewModel() {
    private val _favoriCourses: MutableState<Set<String>> = mutableStateOf(setOf())
    val favoriCourses: MutableState<Set<String>> = _favoriCourses

    fun toggleFavoriCourse(courseCode: String) {
        val updatedSet = _favoriCourses.value.toMutableSet()
        if (updatedSet.contains(courseCode)) {
            updatedSet.remove(courseCode)
        } else {
            updatedSet.add(courseCode)
        }
        _favoriCourses.value = updatedSet
    }
}


