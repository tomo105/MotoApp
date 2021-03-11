package com.example.motoapp.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.motoapp.data.Car
import com.example.motoapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), CarAdapter.OnCarItemLongClick {


    // viewBinding
    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!

    private val LOG_DEBUG = "LOG_DEBUG"

    private val homeVM by viewModels<HomeViewModel>()                                                               // using delegate
    private val adapter = CarAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        Log.d(LOG_DEBUG, "elko w home fragment")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewHome.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewHome.adapter = adapter
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homeVM.cars.observe(viewLifecycleOwner, {list ->
            adapter.setCars(list)
        })
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCarLongClick(car: Car, position: Int) {
        Toast.makeText(requireContext(),car.name, Toast.LENGTH_LONG)
            .show()
    }

}