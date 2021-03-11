package com.example.motoapp.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.motoapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {


    // viewBinding
    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!

    private val LOG_DEBUG = "LOG_DEBUG"

    //private lateinit var viewModel: HomeViewModel
    private val homeVM by viewModels<HomeViewModel>()                           // using delegate

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        Log.d(LOG_DEBUG, "elko w home fragment")
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}