package com.example.motoapp.proflie

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.motoapp.data.User
import com.example.motoapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    // viewBinding
    private var _binding: FragmentProfileBinding? = null
    private val binding
        get() = _binding!!

    private val LOG_DEBUG = "LOG_DEBUG"

    //private lateinit var viewModel: ProfileViewModel
    private val profileVM by viewModels<ProfileViewModel>()                                                                                  // using delegate

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(layoutInflater)
        Log.d(LOG_DEBUG, "witaj w profile fragment")
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        profileVM.user.observe(viewLifecycleOwner, { user ->
            bindUserData(user)
        })

    }

    private fun bindUserData(user: User) {
        Log.d(LOG_DEBUG, user.toString())
        binding.usernameET.setText(user.name)
        binding.userSurnameET.setText(user.surname)
        binding.userEmailET.setText(user.email)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}