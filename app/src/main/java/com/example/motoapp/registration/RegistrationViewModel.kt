package com.example.motoapp.registration

import androidx.lifecycle.ViewModel
import com.example.motoapp.data.User
import com.example.motoapp.repository.FirebaseRepository

class RegistrationViewModel : ViewModel() {
    private val repository = FirebaseRepository()

    fun createNewUser(user: User) {
        repository.createNewUser(user)
    }
}
