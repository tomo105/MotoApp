package com.example.motoapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.motoapp.data.Car
import com.example.motoapp.data.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class FirebaseRepository {
    val LOG_DEBUG = "REPO_DEBUG"

    private val storage = FirebaseStorage.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private val cloud = FirebaseFirestore.getInstance()

    fun getUserData(): LiveData<User> {
        val cloudResult = MutableLiveData<User>()
        val uid = auth.currentUser?.uid

        cloud.collection("users")
            .document(uid!!)
            .get()
            .addOnSuccessListener {
                val user =
                    it.toObject(User::class.java)                                                // map to our class User
                Log.d(LOG_DEBUG, user.toString())
                cloudResult.postValue(user)                                                             // set our liveData
            }
            .addOnFailureListener {
                Log.d(LOG_DEBUG, it.message.toString())
            }
        return cloudResult
    }

    fun getCars(): LiveData<List<Car>> {
        val cloudResult = MutableLiveData<List<Car>>()

        cloud.collection("cars")
            .get()
            .addOnSuccessListener {
                val car = it.toObjects(Car::class.java)                                                // map to our class User
                Log.d(LOG_DEBUG, car.toString())
                cloudResult.postValue(car)                                                             // set our liveData
            }
            .addOnFailureListener {
                Log.d(LOG_DEBUG, it.message.toString())
            }
        return cloudResult
    }


}