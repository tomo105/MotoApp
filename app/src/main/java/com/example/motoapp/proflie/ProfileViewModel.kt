package com.example.motoapp.proflie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.motoapp.data.Car
import com.example.motoapp.repository.FirebaseRepository

class ProfileViewModel : ViewModel() {

    private val repository = FirebaseRepository()
    val user = repository.getUserData()

    val favCars = user.switchMap {
        repository.getFavCar(it.favCars)
    }

    fun removeFavCars(car: Car) {
        repository.removeFavCar(car)
    }

    fun editProfileData(map: Map<String, String>) {
        repository.editProfileData(map)
    }

    fun uploadUserPhoto(bytes: ByteArray) {
        repository.uploadUserPhoto(bytes)
    }

}