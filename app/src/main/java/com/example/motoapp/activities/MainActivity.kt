package com.example.motoapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.motoapp.R
import com.example.motoapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
     private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         binding = ActivityMainBinding.inflate(layoutInflater)
       // setContentView(R.layout.activity_main)

        //   val appBarConfiguration =
        //    AppBarConfiguration(setOf(R.id.homeFragment, R.id.profileFragment))
        //  setupActionBarWithNavController(binding.bottomNav, appBarConfiguration)

    }

}