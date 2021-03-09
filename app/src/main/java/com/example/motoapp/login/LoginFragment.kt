package com.example.motoapp.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.motoapp.R
import com.example.motoapp.databinding.FragmentHomeBinding
import com.example.motoapp.databinding.FragmentSignInBinding

class LoginFragment : Fragment() {
    private var _binding: FragmentSignInBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSignInBinding.inflate(layoutInflater, container, false)
        return binding.root
      //  return inflater.inflate(R.layout.fragment_sign_in, container, false)


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}