package com.example.motoapp

import android.content.Intent
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.motoapp.activities.MainActivity
import com.example.motoapp.registration.RegistrationViewModel

// abstract class which we use in login fragment and registration activity and registration fragment --> so it will extends this BaseFragment
// it is a Fragment with additional function startApp --> to not do duplicate code !!!
abstract class BaseFragment : Fragment() {

    private val baseVM by viewModels<BaseViewModel>()
    private val LOG_DEBUG = "LOG_DEBUG"
    protected fun startApp() {

        baseVM.isAdmin.observe(viewLifecycleOwner, { isAdmin ->
            Log.d(LOG_DEBUG,isAdmin.toString())
         //   isAdmin?.let {
                if (isAdmin) {                                                                          // isAdmin can be true if added in firebase firestore !!!!
                    Log.d(LOG_DEBUG, "observe user in base ")

                    val intent = Intent(requireContext(), MainActivity::class.java).apply {
                        flags = (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        // if you could go back here i you are logged in !!
                    }
                    startActivity(intent)
                } else {
                    Log.d(LOG_DEBUG, "observe user in base - NOT ADMIN!!! u")
                }
        //    }
        })
    }
}