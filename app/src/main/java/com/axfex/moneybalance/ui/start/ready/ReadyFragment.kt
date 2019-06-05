package com.axfex.moneybalance.ui.start.signin

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.axfex.moneybalance.R
import com.axfex.moneybalance.core.AppFragment
//import com.axfex.moneybalance.databinding.FragmentSignupBinding
import com.axfex.moneybalance.utils.*
import kotlinx.android.synthetic.main.fragment_ready.*
import javax.inject.Inject


class ReadyFragment : AppFragment() {

    @Inject
    lateinit var viewModel: ReadyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        val binding = FragmentSignupBinding.inflate(inflater, container, false)
//        binding.signIn.setOnClickListener {
//            viewModel.login(
//                binding.email.text.toString(), binding.password.text.toString()
//            )
//        }
//
//        return binding.root
        return inflater.inflate(R.layout.fragment_ready,container,false)
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        start.setOnClickListener {
            viewModel.completeFirstStart()
            findNavController().navigate(R.id.action_to_mainActivity)
        activity?.finish()}
    }

}
