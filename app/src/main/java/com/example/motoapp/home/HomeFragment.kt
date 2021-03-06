package com.example.motoapp.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.motoapp.R
import com.example.motoapp.activities.MainActivity
import com.example.motoapp.activities.RegistrationActivity
import com.example.motoapp.data.Car
import com.example.motoapp.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth

class HomeFragment : Fragment(), CarAdapter.OnCarItemLongClick {


    // viewBinding
    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!

    private val LOG_DEBUG = "LOG_DEBUG"

    private val homeVM by viewModels<HomeViewModel>()                                                               // using delegate
    private val adapter = CarAdapter(this)                                                                   // listening to our fragment

    private val fbAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        Log.d(LOG_DEBUG, "elko w home fragment")
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.logout_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.logout_action -> {
                fbAuth.signOut()
                // requireActivity().finish()
                val intent = Intent(requireContext(), RegistrationActivity::class.java)
                startActivity(intent)
            }

        }
        return false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewHome.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewHome.adapter = adapter

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
                                                                                                                                // observe on liveData
        homeVM.cars.observe(viewLifecycleOwner, {list ->
            adapter.setCars(list)
        })
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCarLongClick(car: Car, position: Int) {
        homeVM.addFavCar(car)
        Toast.makeText(requireContext(),car.name, Toast.LENGTH_LONG)                                                            // show what we clicked
            .show()
    }

}