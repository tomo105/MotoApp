package com.example.motoapp.home

import androidx.lifecycle.ViewModel
import com.example.motoapp.repository.FirebaseRepository

class HomeViewModel : ViewModel() {
            private val repository = FirebaseRepository()
            val  cars = repository.getCars()

}