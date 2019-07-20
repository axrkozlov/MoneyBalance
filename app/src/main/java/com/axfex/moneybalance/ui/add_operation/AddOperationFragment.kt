package com.axfex.moneybalance.ui.add_operation

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.axfex.moneybalance.R
import com.axfex.moneybalance.core.AppFragment
import com.axfex.moneybalance.ui.main.MainViewModel
import com.axfex.moneybalance.utils.subscribe
import kotlinx.android.synthetic.main.fragment_add_operation.*
import kotlinx.android.synthetic.main.fragment_add_operation.categoryName
import kotlinx.android.synthetic.main.fragment_category_dialog_item.view.*
import javax.inject.Inject

class AddOperationFragment : AppFragment() {

    @Inject
    lateinit var viewModel: AddOperationViewModel

    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_add_operation, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        categoryLayout.setOnClickListener{
            findNavController().navigate(R.id.action_show_categoryDialog)
        }

        mainViewModel.getPickedExpenseCategory().subscribe(this){tuple->
            tuple?.let {
                viewModel.category(it.k,it.v)?.subscribe(this){category ->
                    categoryName.text=category.name
                    categoryIcon.setImageDrawable(viewModel.getIconDrawable(category.iconName))
                    val drawable = categoryColor.categoryColor.drawable.mutate() as GradientDrawable
                    drawable.setColor(category.color)


                }
            }
        }

        mainViewModel.getPickedAccount().subscribe(this) { accountId ->

            accountId?.let {account ->
                viewModel.account(account).subscribe(this){
                    accountName.text=it.name
                    accountIcon.setImageDrawable(viewModel.getIconDrawable(it.iconName))
                    val drawable= accountIcon.drawable
                    drawable.setTint(it.color)
                }
            }

        }

        accountLayout.setOnClickListener{
            findNavController().navigate(R.id.action_show_accountDialog)
        }

    }


}
