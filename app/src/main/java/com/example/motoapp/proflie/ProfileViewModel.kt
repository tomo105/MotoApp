package com.example.motoapp.proflie

import androidx.lifecycle.ViewModel
import com.example.motoapp.repository.FirebaseRepository

class ProfileViewModel : ViewModel() {

    private val repository = FirebaseRepository()
    val user = repository.getUserData()

}