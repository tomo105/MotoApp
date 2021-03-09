package com.example.motoapp.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.motoapp.R
import com.example.motoapp.databinding.FragmentSignUpBinding

class RegistrationFragment : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        _binding = FragmentSignUpBinding.inflate(layoutInflater, container, false)
        return binding.root
        //   return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}