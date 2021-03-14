package com.example.motoapp.registration

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.motoapp.BaseFragment
import com.example.motoapp.data.User
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

    private val regVM by viewModels<RegistrationViewModel>()                                                                                  // using delegate

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

           // val username = binding.usernameRegistration.text?.trim().toString()
            val email = binding.emailRegistration.text?.trim().toString()
            val pass = binding.passRegistration.text?.trim().toString()
            val passConfirmed = binding.repeatPassRegistration.text?.trim().toString()

            // walidacja TODO !!!!!!!!!

            if (pass == passConfirmed) {
                fbAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnSuccessListener { authRes ->
                        if (authRes.user != null) {
                            val user = User(authRes.user!!.uid, false, "", "", authRes.user!!.email, listOf())
                            regVM.createNewUser(user)                                                                                             //  // add to cloud firestore db !!!
                            startApp()
                        }
                    }
                    .addOnFailureListener { exc ->
                        Snackbar.make(
                            requireView(),
                            "Upss .. sth goes wrong with registration :/",
                            Snackbar.LENGTH_SHORT
                        )
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