package com.axfex.moneybalance.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.axfex.moneybalance.R
import com.axfex.moneybalance.core.AppFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ProfileFragment : AppFragment() {


    @Inject
    lateinit var viewModel: ProfileViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        logOut2.setOnClickListener { viewModel.logOut() }
        token.setOnClickListener { viewModel.user()?.getIdToken(true)?.addOnSuccessListener {
            Log.i("ProfileFragment", "onActivityCreated (line 32): ${it.token.toString()}")
            val expiration=it.expirationTimestamp
            val date = Date(expiration!!)
            val format = SimpleDateFormat("dd/mm/yy HH:mm:ss")
            Log.i("UserManager", "signIn (line 56): ${date.toLocaleString()}")
        } }
        expired.setOnClickListener {
             viewModel.user()?.getIdToken(false)?.addOnSuccessListener {

                 val expiration=it.expirationTimestamp
            val date = Date(expiration!!)
            val format = SimpleDateFormat("dd/mm/yy HH:mm:ss")
            Log.i("UserManager", "signIn (line 56): ${date.toLocaleString()}, nul=${Date(0).toLocaleString()}")
        }
//            Log.i("ProfileFragment", "onActivityCreated (line 35): ${TimeUnit.MILLISECONDS.toMinutes(expiration!!)}")
        }
    }

}
