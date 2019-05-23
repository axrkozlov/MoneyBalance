package com.axfex.moneybalance.ui.splash

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.axfex.moneybalance.R
import com.axfex.moneybalance.domain.User.*
import com.axfex.moneybalance.utils.getViewModel


class SplashFragment : Fragment() {
    private lateinit var splashViewModel: SplashViewModel
    private val handler= Handler()
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        splashViewModel = getViewModel()
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed({
            when (splashViewModel.currentUser.value){
                is NotAuthenticated -> findNavController().navigate(R.id.action_splashFragment_to_fragment_login)
                is AuthenticatedUser -> findNavController().navigate(R.id.action_splashFragment_to_fragment_balance)
            }
        },200)

    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacksAndMessages(null)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

}
