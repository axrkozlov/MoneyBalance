package com.axfex.moneybalance.ui.category.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.*
import com.axfex.moneybalance.R
import com.axfex.moneybalance.domain.model.icon.Icon

class EditCategoryIconAdapter(val viewModel: EditCategoryViewModel) :
    ListAdapter<Icon, EditCategoryIconAdapter.ViewHolder>(DrawableDiffCallback()) {

    private lateinit var recyclerView: RecyclerView
    private var iconList: List<Icon>? = null

    var selectedIconName: String = ""
    var selectedPosition: Int = 0

    private var pendingSelectionIconName: String = ""

    private var selectionCallback: ((selectedIconName: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_edit_category_icon_item, parent, false)
        return ViewHolder(view)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    fun select(position: Int): Boolean {
        if (position < 0 || position >= itemCount) return false

        selectedIconName = getItem(position).name
        selectionCallback?.let { it(getItem(position).name) }
        val previous = selectedPosition
        selectedPosition = position
        notifyItemChanged(previous, "payload $previous")
        notifyItemChanged(selectedPosition, "payload $selectedPosition")
        scrollToSelected()
        return true
    }

    fun selectByName(iconName: String) {
        iconList?.let {
            for (i in 0 until it.size) {
                if (it[i].name == iconName) {
                    if (select(i)) {
                        pendingSelectionIconName = ""
                        return@selectByName
                    }
                }
            }
        }
        pendingSelectionIconName = iconName
    }

    override fun submitList(list: List<Icon>?) {
        super.submitList(list)
        iconList = list
        if (pendingSelectionIconName.isNotEmpty()) selectByName(pendingSelectionIconName)
        else select(selectedPosition)
    }

    private fun scrollToSelected() {
        if (
            (recyclerView.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition() > selectedPosition ||
            (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition() < selectedPosition
        ) recyclerView.scrollToPosition(selectedPosition)
    }

    fun setSelectionChangeCallback(callback: (selectedIconName: String) -> Unit) {
        selectionCallback = callback

    }

    fun onSaveInstanceState(state: Bundle?) {
        state?.putInt("selectedIconName", selectedPosition)
    }

    fun onRestoreInstanceState(state: Bundle?) {
        state?.let {
            selectedPosition = it.getInt("selectedIconName", 0)
        }
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(icon: Icon, position: Int) {


            val image = view.findViewById<ImageView>(R.id.categoryIcon)
            val name = view.findViewById<TextView>(R.id.categoryIconName)

            itemView.setOnClickListener {
                select(position)
            }
            itemView.isActivated = position == selectedPosition




            viewModel.getIconDrawable(icon.name)
            image.setImageDrawable(viewModel.getIconDrawable(icon.name))
            name.text = icon.name.split(".")[0]


        }

    }

    class DrawableDiffCallback : DiffUtil.ItemCallback<Icon>() {
        override fun areContentsTheSame(oldItem: Icon, newItem: Icon): Boolean {
            return newItem == oldItem
        }

        override fun areItemsTheSame(oldItem: Icon, newItem: Icon): Boolean {
            return oldItem == newItem
        }

    }




}

