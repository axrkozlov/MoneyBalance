package com.axfex.moneybalance.ui.category.list

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import android.view.LayoutInflater
import androidx.viewpager.widget.ViewPager
import com.axfex.moneybalance.R
import com.axfex.moneybalance.domain.model.category.CategoryType
import kotlinx.android.synthetic.main.fragment_category_list_tab.view.*


class CategoryListPagerAdapter(
    val context: Context,
    val expenseAdapter: CategoryListAdapter,
    val incomeAdapter: CategoryListAdapter
) :
    PagerAdapter() {


    private val pageExpenseName: String = context.getString(R.string.categoryListPageExpenceCategories)
    private val pageIncomeName: String = context.getString(R.string.categoryListPageIncomeCategories)

    private var viewPager: ViewPager? = null

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        if (container is ViewPager) viewPager = container
        val view = LayoutInflater.from(context).inflate(
            R.layout.fragment_category_list_tab,
            container,
            false
        ) as ViewGroup
        when (position) {
            0 -> view.categoryListRecycler.adapter = expenseAdapter
            1 -> view.categoryListRecycler.adapter = incomeAdapter
        }
        container.addView(view)
        return view

    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }

    override fun getCount(): Int {
        return 2
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> pageExpenseName
            1 -> pageIncomeName
            else -> ""
        }
    }


    fun currentTab(): CategoryType {
        return when (viewPager?.currentItem) {
            0 -> CategoryType.EXPENSE_CATEGORY
            1 -> CategoryType.INCOME_CATEGORY
            else -> throw Exception("wrong incomeCategory tab number")
        }

    }



}