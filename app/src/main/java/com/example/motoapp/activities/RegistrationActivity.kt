package com.example.motoapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.motoapp.databinding.ActivityRegistrationBinding

import com.google.firebase.auth.FirebaseAuth

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding
    private val fbAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

          binding = ActivityRegistrationBinding.inflate(layoutInflater)
         setContentView(binding.root)
//            setContentView(R.layout.activity_registration)

    }

    override fun onStart() {
        super.onStart()
        isCurrentUser()

    }

    private fun isCurrentUser() {
        fbAuth.currentUser?.let { auth ->
            val intent = Intent(applicationContext, MainActivity::class.java).apply {
                flags = (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                                                                                            // if you you could go baack here i you are logged in !!
            }
            startActivity(intent)
        }
    }


}