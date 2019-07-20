package com.axfex.moneybalance.ui.category.list

import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.findNavController
import com.axfex.moneybalance.R
import com.axfex.moneybalance.core.AppFragment
import com.axfex.moneybalance.domain.model.category.CategoryType
import com.axfex.moneybalance.utils.subscribe
import kotlinx.android.synthetic.main.fragment_category_list_pager.*
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


        viewModel.categoryList().subscribe(this){ list ->
            val expenseCategoryList=list.filter{
                it.type==CategoryType.EXPENSE_CATEGORY
            }
            adapter.expenseAdapter.submitList(expenseCategoryList)
            val incomeCategoryList=list.filter{
                it.type==CategoryType.INCOME_CATEGORY
            }
            adapter.incomeAdapter.submitList(incomeCategoryList)

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
