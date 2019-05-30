package com.axfex.moneybalance.ui.welcome

import android.graphics.Color
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.axfex.moneybalance.R
import com.axfex.moneybalance.utils.getViewModel
import kotlinx.android.synthetic.main.fragment_welcome.*
import kotlinx.android.synthetic.main.fragment_welcome_indicator.*



class WelcomeFragment : Fragment() {

    companion object {
        fun newInstance() = WelcomeFragment()
    }

    private lateinit var viewModel: WelcomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_welcome, container, false)

//        val radius = 5f
//
//        val decorView = activity?.window?.decorView
//
//        val rootView = decorView?.findViewById(android.R.id.content) as ViewGroup
//
//        val windowBackground = decorView.background
//
//        (view.findViewById<View>(R.id.viewWelcome) as BlurView).setupWith(rootView)
//            .setFrameClearDrawable(windowBackground)
//            .setBlurAlgorithm(RenderScriptBlur(context))
//            .setBlurRadius(radius)
//            .setHasFixedTransformationMatrix(false)
        return view
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = getViewModel()
        signIn.setOnClickListener { findNavController().navigate(R.id.action_welcomeFragment_to_signInFragment) }
        skip.setOnClickListener { viewModel.signInAnonimously() }
    }

}
