package com.example.motoapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import com.example.motoapp.repository.FirebaseRepository

class BaseViewModel : ViewModel() {
    private val repository = FirebaseRepository()
    val isAdmin = repository.getUserAdminInfo()

}