package com.axfex.moneybalance.ui.account

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import com.axfex.moneybalance.R
import com.axfex.moneybalance.ui.account.AccountPagerAdapter.CustomPagerEnum
import kotlinx.android.synthetic.main.fragment_account_item.view.*


class AccountPagerAdapter : PagerAdapter() {

    enum class CustomPagerEnum constructor(val title: String, val color: Int) {

        RED("red", Color.RED),
        BLUE("blue", Color.BLUE)

    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val color=CustomPagerEnum.values()[position]
        val inflater = LayoutInflater.from(container.context)
        val view = inflater.inflate(R.layout.fragment_account_item, container, false) as ViewGroup
        view.accountName.setTextColor(color.color)
        container.addView(view)
        return view
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }

    override fun getCount(): Int {
        return CustomPagerEnum.values().size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val color = CustomPagerEnum.values()[position]
        return color.title
    }



}