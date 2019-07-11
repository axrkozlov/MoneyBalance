package com.axfex.moneybalance.ui.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.axfex.moneybalance.R
import kotlinx.android.synthetic.main.fragment_account_list.*
import javax.inject.Inject

class AccountListFragment : Fragment() {

    @Inject
    lateinit var viewModel: AccountListViewModel

    @Inject
    lateinit var adapter: AccountListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_account_list, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryListPager.adapter=AccountPagerAdapter()
        tablayout.setupWithViewPager(categoryListPager)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // TODO: Use the ViewModel
    }

}
