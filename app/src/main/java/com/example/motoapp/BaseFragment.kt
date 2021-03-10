package com.example.motoapp

import android.content.Intent
import androidx.fragment.app.Fragment
import com.example.motoapp.activities.MainActivity

    // abstract class which we use in login fragment and registration activity and registration fragment --> so it will extends this BaseFragment
    // it is a Fragment with additional function startApp --> to not do duplicate code !!!
abstract class BaseFragment : Fragment() {

    protected fun startApp() {
        val intent = Intent(requireContext(), MainActivity::class.java).apply {
            flags = (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            // if you you could go baack here i you are logged in !!
        }
        startActivity(intent)
    }
}