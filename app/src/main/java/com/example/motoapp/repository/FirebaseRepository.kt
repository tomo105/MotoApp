package com.example.motoapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.motoapp.data.Car
import com.example.motoapp.data.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FieldValue
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

    fun getUserAdminInfo(): LiveData<Boolean> {
        val cloudResult = MutableLiveData<Boolean>()
        val uid = auth.currentUser?.uid

        cloud.collection("users")
            .document(uid!!)
            .get()
            .addOnSuccessListener {
                val user =
                    it.toObject(User::class.java)                                                // map to our class User
                Log.d(LOG_DEBUG, user.toString())

                cloudResult.postValue(user!!.admin)                                                             // set our liveData
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
                val car =
                    it.toObjects(Car::class.java)                                                // map to our class User
                Log.d(LOG_DEBUG, car.toString())
                cloudResult.postValue(car)                                                             // set our liveData
            }
            .addOnFailureListener {
                Log.d(LOG_DEBUG, it.message.toString())
            }
        return cloudResult
    }


    fun addFavCar(car: Car) {
        cloud.collection("users")
            .document(auth.currentUser?.uid!!)
            .update(
                "favCars",
                FieldValue.arrayUnion(car.uid)
            )                                       // add to array named favCars!!!! important !!!
            .addOnSuccessListener {
                Log.d(LOG_DEBUG, "Added to fav cars")
            }
            .addOnFailureListener {
                Log.d(LOG_DEBUG, it.message.toString())
            }

    }

    fun removeFavCar(car: Car) {
        cloud.collection("users")
            .document(auth.currentUser?.uid!!)
            .update("favCars", FieldValue.arrayRemove(car.uid))                                       // add to array named favCars!!!! important !!!
            .addOnSuccessListener {
                Log.d(LOG_DEBUG, "Added to fav cars")
            }
            .addOnFailureListener {
                Log.d(LOG_DEBUG, it.message.toString())
            }

    }

    fun getFavCar(list: List<String>?): LiveData<List<Car>> {

        val carListResult = MutableLiveData<List<Car>>()

        if (!list.isNullOrEmpty()) {
            cloud.collection("cars")
                .whereIn("uid", list)                                                               //  to check if id == list so we get document who matched !!!!!
                .get()
                .addOnSuccessListener {
                    Log.d(LOG_DEBUG, "you get a list of fav cars")
                    val resultList = it.toObjects(Car::class.java)
                    carListResult.postValue(resultList)
                }
                .addOnFailureListener {
                    Log.d(LOG_DEBUG, it.message.toString())
                }
        }

        return carListResult
    }

    fun createNewUser(user :User) {
        cloud.collection("users")
            .document(user.uid!!)
            .set(user)
    }

    fun editProfileData(map :Map<String,String>){
        cloud.collection("users")
            .document(auth.currentUser!!.uid)
            .update(map)
            .addOnSuccessListener {
                Log.d(LOG_DEBUG, "Updated user info")
            }
            .addOnFailureListener {
                Log.d(LOG_DEBUG, it.message.toString())
            }

    }
}