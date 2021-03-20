package com.example.motoapp.login


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.motoapp.BaseFragment
import com.example.motoapp.databinding.FragmentSignInBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : BaseFragment() {

    // viewBinding
    private var _binding: FragmentSignInBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var navController: NavController

    // firebase
    private val fbAuth = FirebaseAuth.getInstance()

    // logi
    private val LOG_DEBUG = "LOG_DEBUG"


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSignInBinding.inflate(layoutInflater, container, false)
        return binding.root
        //  return inflater.inflate(R.layout.fragment_sign_in, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupLoginClick()
        setupRegistrationClick()

    }

    private fun setupRegistrationClick() {
        // move to RegistrationFragment
        binding.signUpButtonLogin.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegistrationFragment().actionId)
        }
    }

    private fun setupLoginClick() {
        // log in user
        binding.loginButton.setOnClickListener {
            val email = binding.emailLoginInput.text?.trim().toString()
            val password = binding.passLoginInput.text?.trim().toString()

            fbAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener{ authRes ->
                    if (authRes.user != null)
                        Log.d(LOG_DEBUG,"$email logged in")
                        startApp()                                                                                                 // fun from BaseFragment
                }
                .addOnFailureListener { exc ->
                    Snackbar.make(requireView(), "Upss .. sth goes wrong with login :/", Snackbar.LENGTH_SHORT)
                        .show()
                    Log.d(LOG_DEBUG, "ERROR IN LOGIN: " + exc.message.toString())
                }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}