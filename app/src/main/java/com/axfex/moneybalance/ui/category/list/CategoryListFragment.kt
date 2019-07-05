package com.axfex.moneybalance.ui.category.list

import android.os.Bundle
import android.view.*
import com.axfex.moneybalance.R
import com.axfex.moneybalance.core.AppFragment
import kotlinx.android.synthetic.main.fragment_category_list.*
import javax.inject.Inject

class CategoryListFragment : AppFragment() {

    @Inject
    lateinit var viewModel: CategoryListViewModel

    @Inject
    lateinit var adapter: CategoryListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryRecycler.adapter=adapter
        viewModel.dosmth()

    }
}
