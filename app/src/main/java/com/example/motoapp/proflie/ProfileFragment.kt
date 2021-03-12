package com.example.motoapp.proflie

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.motoapp.data.Car
import com.example.motoapp.data.User
import com.example.motoapp.databinding.FragmentProfileBinding
import com.example.motoapp.home.CarAdapter

class ProfileFragment : Fragment(), CarAdapter.OnCarItemLongClick{

    // viewBinding
    private var _binding: FragmentProfileBinding? = null
    private val binding
        get() = _binding!!

    private val LOG_DEBUG = "LOG_DEBUG"

    //private lateinit var viewModel: ProfileViewModel
    private val profileVM by viewModels<ProfileViewModel>()                                                                                  // using delegate

    private val adapter = CarAdapter(this)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewFavCars.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewFavCars.adapter = adapter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(layoutInflater)
        Log.d(LOG_DEBUG, "witaj w profile fragment")
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        profileVM.user.observe(viewLifecycleOwner, { user ->
            bindUserData(user)
        })

        profileVM.favCars.observe( viewLifecycleOwner, { listCars ->
            listCars?.let{
                Log.d(LOG_DEBUG, listCars.toString())
                adapter.setCars(it)
            }

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

    override fun onCarLongClick(car: Car, position: Int) {                      //remove from  fav car List on long item click
          profileVM.removeFavCars(car)
          adapter.removeCar(car, position)
    }

}