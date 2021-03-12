package com.example.motoapp.data

data class User(
    val uid: String? = null,
    val name: String? = null,
    val surname: String? = null,
    val email: String? = null,
    val favCars: List<String>? = null,
    val image: String? = null
)
                                                    // same name in data package and firebase repo !!!!!!