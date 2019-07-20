package com.axfex.moneybalance.ui.account.dialog

import android.animation.ValueAnimator
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.axfex.moneybalance.R
import com.axfex.moneybalance.core.AppChoiceDialogFragment
import com.axfex.moneybalance.utils.subscribe
import kotlinx.android.synthetic.main.fragment_account_dialog.*
import javax.inject.Inject

class AccountDialogFragment : AppChoiceDialogFragment() {

    @Inject
    lateinit var viewModel: AccountDialogViewModel


    @Inject
    lateinit var adapter: AccountDialogAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        observe()




    }

    private fun observe() {
        viewModel.accountList().subscribe(this){ list ->
            adapter.submitList(list)
        }
    }

    private fun setupUI() {

        accountDialogRecycler.adapter = adapter
        adapter.dismissCallback= { dismiss() }


    }

}
