package com.axfex.moneybalance.ui.operation.list

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.axfex.moneybalance.R
import com.axfex.moneybalance.domain.model.operation.OperationListView
import com.axfex.moneybalance.domain.model.operation.OperationType
import com.axfex.moneybalance.utils.format
import kotlinx.android.synthetic.main.fragment_operation_list_expense_item.view.*
import java.lang.IllegalArgumentException

class OperationListPagedAdapter (val viewModel: OperationListViewModel) :
    PagedListAdapter<OperationListView, OperationListPagedAdapter.ViewHolder>(OperationDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = when (viewType){
            0-> LayoutInflater.from(parent.context).inflate(R.layout.fragment_operation_list_income_item, parent, false)
            1 -> LayoutInflater.from(parent.context).inflate(R.layout.fragment_operation_list_expense_item, parent, false)
            else ->throw IllegalArgumentException("OperationListAdapter:Can't find view by type")
        }

        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {

            holder.bind(it)
        }
    }

    override fun getItemViewType(position: Int): Int {
        getItem(position)?.let {
        return when (it.type) {
            OperationType.INCOME -> 0
            OperationType.EXPENSE -> 1
            OperationType.IN_TRANSFER -> 2
            OperationType.OUT_TRANSFER -> 3
        }
        }
        return 0
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(operation: OperationListView) {

            itemView.note.text = operation.note
            itemView.categoryLabel.text= operation.categoryName
            itemView.amount.text=operation.amount.format()

            itemView.categoryIcon.setImageDrawable(viewModel.getIconDrawable(operation.categoryIconName))

            val background = itemView.categoryIcon.background.mutate() as GradientDrawable
            background.setColor(operation.categoryColor)
//
//            itemView.setOnClickListener {
//                val direction =
//                    OperationListFragmentDirections.actionOperationListFragmentToEditOperationFragment(operation.id,operation.type)
//
//                itemView.findNavController().navigate(direction)
//            }
        }

    }

    class OperationDiffCallback : DiffUtil.ItemCallback<OperationListView>() {
        override fun areContentsTheSame(oldItem: OperationListView, newItem: OperationListView): Boolean {
            return newItem == oldItem
        }

        override fun areItemsTheSame(oldItem: OperationListView, newItem: OperationListView): Boolean {
            return oldItem == newItem
        }

    }

}