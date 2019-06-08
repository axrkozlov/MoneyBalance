package com.axfex.moneybalance.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.axfex.moneybalance.R
import com.axfex.moneybalance.core.AppFragment
import javax.inject.Inject

class CategoryEditFragment : AppFragment() {

    companion object {
        fun newInstance() = CategoryEditFragment()
    }

    @Inject
    lateinit var viewModel: CategoryEditViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category_edit, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // TODO: Use the ViewModel
    }



}
