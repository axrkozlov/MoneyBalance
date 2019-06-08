package com.axfex.moneybalance.ui.start.splash

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.axfex.moneybalance.R
import com.axfex.moneybalance.core.AppFragment
import javax.inject.Inject


class SplashFragment : AppFragment() {


    @Inject
    lateinit var viewModel: SplashViewModel

    private val handler= Handler()
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed({
            if (viewModel.isFirstStartComplete) {
                findNavController().navigate(R.id.action_to_mainActivity)
                activity?.finish()
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_welcomeFragment)
            }
//            when (viewModel.currentUser.value){
//                is NotAuthenticated -> findNavController().navigate(R.id.action_splashFragment_to_welcomeFragment)
//                is Authenticated -> findNavController().navigate(R.id.action_to_balanceFragment)
//            }
        },20)

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
