package com.axfex.moneybalance.ui.login

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.axfex.moneybalance.databinding.FragmentSignupBinding
import com.axfex.moneybalance.ui.login.SignUpViewModel.SignUpViewState
import com.axfex.moneybalance.utils.*


class SignUpFragment : Fragment() {

    private lateinit var viewModel: SignUpViewModel
    val TAG = "SignUpFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentSignupBinding.inflate(inflater, container, false)
        binding.signIn.setOnClickListener {
            viewModel.login(
                binding.email.text.toString(), binding.password.text.toString()
            )
        }

        return binding.root
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = getViewModel()
        viewModel.state.subscribe(this, this::render)
    }

    private fun render(state: SignUpViewState) {


    }

}
