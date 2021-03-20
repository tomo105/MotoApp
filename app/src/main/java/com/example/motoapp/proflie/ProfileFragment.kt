package com.example.motoapp.proflie

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.motoapp.data.Car
import com.example.motoapp.data.User
import com.example.motoapp.databinding.FragmentProfileBinding
import com.example.motoapp.home.CarAdapter
import java.io.ByteArrayOutputStream
import java.lang.Exception

class ProfileFragment : Fragment(), CarAdapter.OnCarItemLongClick {

    // viewBinding
    private var _binding: FragmentProfileBinding? = null
    private val binding
        get() = _binding!!

    private val LOG_DEBUG = "LOG_DEBUG"
    private val REQUEST_IMAGE_CAPTURE = 123

    //private lateinit var viewModel: ProfileViewModel
    private val profileVM by viewModels<ProfileViewModel>()                                                                                  // using delegate

    private val adapter = CarAdapter(this)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSubmitDataClick()
        setupTakePictureClick()
        binding.recyclerViewFavCars.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewFavCars.adapter = adapter
    }

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

        profileVM.favCars.observe(viewLifecycleOwner, { listCars ->
            listCars?.let {
                Log.d(LOG_DEBUG, listCars.toString())
                adapter.setCars(it)
            }

        })

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCarLongClick(car: Car, position: Int) {                      //remove from  fav car List on long item click
        profileVM.removeFavCars(car)
        adapter.removeCar(car, position)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(LOG_DEBUG, "BITMAP: $requestCode  $resultCode")
        Log.d(LOG_DEBUG, "BITMAP: $RESULT_OK")

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val image = data?.extras?.get("data") as Bitmap

            Log.d(LOG_DEBUG, "BITMAP: " + image.byteCount.toString())

            Glide.with(this)
                .load(image)
                .circleCrop()
                .disallowHardwareConfig()
                .into(binding.userImage)

            val stream = ByteArrayOutputStream()
            val result = image.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            val byteArray = stream.toByteArray()                                                        // convert to byteArray

            if (result)
                profileVM.uploadUserPhoto(byteArray)

        }
    }


    private fun setupTakePictureClick() {
        binding.userImage.setOnClickListener {
            takePicture()
        }
    }

    private fun takePicture() {
        try {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE)
            Log.d(LOG_DEBUG,  "Taking picture")
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
        } catch (e: Exception) {
            Log.d(LOG_DEBUG, e.message.toString())
        }
    }

    private fun bindUserData(user: User) {
        Log.d(LOG_DEBUG, user.toString())
        binding.usernameET.setText(user.name)
        binding.userSurnameET.setText(user.surname)
        binding.userEmailET.text = user.email
        Glide.with(this)
            .load(user.image)
            .circleCrop()
            .into(binding.userImage)
    }

    private fun setupSubmitDataClick() {
        binding.sumbitDataProfileButton.setOnClickListener {
            val name = binding.usernameET.text.trim().toString()
            val surname = binding.userSurnameET.text.trim().toString()

            val map = mapOf("name" to name, "surname" to surname)
            Log.d(LOG_DEBUG, map.toString())
            profileVM.editProfileData(map)
        }
    }


}