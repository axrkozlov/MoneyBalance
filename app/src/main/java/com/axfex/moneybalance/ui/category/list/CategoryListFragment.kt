package com.axfex.moneybalance.ui.category.list

import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.findNavController
import com.axfex.moneybalance.R
import com.axfex.moneybalance.core.AppFragment
import com.axfex.moneybalance.utils.subscribe
import kotlinx.android.synthetic.main.fragment_account_list.*
import javax.inject.Inject

class CategoryListFragment : AppFragment() {

    @Inject
    lateinit var viewModel: CategoryListViewModel

    @Inject
    lateinit var adapter: CategoryListPagerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category_list_pager, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        categoryListRecycler.adapter=adapter
        viewModel.expenseCategoryList().subscribe(this){
            adapter.expenseAdapter.submitList(it)
        }

        viewModel.incomeCategoryList().subscribe(this){
            adapter.incomeAdapter.submitList(it)
        }

        categoryListPager.adapter= adapter
        tablayout.setupWithViewPager(categoryListPager)



    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.category_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.add -> {
                val direction =
                    CategoryListFragmentDirections.actionCategoryListFragmentToEditCategoryFragment(categoryType = adapter.currentTab() )
                findNavController().navigate(direction)
//                findNavController().navigate(R.id.action_categoryListFragment_to_editCategoryFragment)


                
            }
        }

        return super.onOptionsItemSelected(item)
    }
}