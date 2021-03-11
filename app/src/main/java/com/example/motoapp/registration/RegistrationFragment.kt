package com.example.motoapp.registration

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.motoapp.BaseFragment
import com.example.motoapp.databinding.FragmentSignUpBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class RegistrationFragment : BaseFragment() {

    // binding
    private var _binding: FragmentSignUpBinding? = null
    private val binding
        get() = _binding!!

    // log debug
    private val LOG_DEBUG = "LOG_DEBUG"

    // firebase
    private var fbAuth = FirebaseAuth.getInstance()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        _binding = FragmentSignUpBinding.inflate(layoutInflater, container, false)
        return binding.root
        //   return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSignUpClick()
    }

    private fun setupSignUpClick() {

        binding.signUpButtonRegister.setOnClickListener {

            val username = binding.usernameRegistration.text?.trim().toString()
            val email = binding.emailRegistration.text?.trim().toString()
            val password = binding.passRegistration.text?.trim().toString()
            val rPassword = binding.repeatPassRegistration.text?.trim().toString()

            // walidacja TODO !!!!!!!!!
            if (password == rPassword ) {
                fbAuth.createUserWithEmailAndPassword(email,password)
                    .addOnSuccessListener { authRes ->
                        if (authRes.user != null)
                            startApp()

                    }
                    .addOnFailureListener {     exc ->
                        Snackbar.make(requireView(), "Upss .. sth goes wrong with registration :/", Snackbar.LENGTH_SHORT)
                            .show()
                        Log.d(LOG_DEBUG, exc.message.toString())
                    }
            }



        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}