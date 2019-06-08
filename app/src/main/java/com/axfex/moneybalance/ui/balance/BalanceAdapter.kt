package com.axfex.moneybalance.ui.balance

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axfex.moneybalance.R

class BalanceAdapter: RecyclerView.Adapter<BalanceAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.fragment_category_dialog_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return 50;
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    class ViewHolder(view : View): RecyclerView.ViewHolder(view){

    }
}
