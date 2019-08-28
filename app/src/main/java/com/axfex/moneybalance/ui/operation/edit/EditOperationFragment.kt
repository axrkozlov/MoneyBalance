package com.axfex.moneybalance.ui.operation.edit

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.*
import com.axfex.moneybalance.R
import com.axfex.moneybalance.core.AppFragment
import com.axfex.moneybalance.domain.model.category.CategoryType
import com.axfex.moneybalance.domain.model.operation.OperationType
import com.axfex.moneybalance.ui.category.dialog.CategoryDialogFragmentArgs
import com.axfex.moneybalance.ui.category.dialog.CategoryDialogFragmentDirections
import com.axfex.moneybalance.ui.main.MainViewModel
import com.axfex.moneybalance.utils.hideKeyboard
import com.axfex.moneybalance.utils.subscribe
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_edit_operation.*
import kotlinx.android.synthetic.main.fragment_edit_operation.categoryName
import kotlinx.android.synthetic.main.fragment_edit_operation.totalIncomes
import kotlinx.android.synthetic.main.fragment_edit_operation.view.*
import javax.inject.Inject

class EditOperationFragment : AppFragment() {

    @Inject
    lateinit var viewModel: EditOperationViewModel

    @Inject
    lateinit var mainViewModel: MainViewModel

    var accountId:String?=null
    var categoryId:String?=null

    private val args: EditOperationFragmentArgs by navArgs()
    private val operationType: OperationType by lazy { args.operationType }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_operation, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.operationTypeEvent.setValue(operationType)

        categoryLayout.setOnClickListener{
            val categoryType= when (viewModel.operationTypeEvent.value){
                OperationType.INCOME->{
                    CategoryType.INCOME_CATEGORY
                }
                OperationType.EXPENSE->{
                    CategoryType.EXPENSE_CATEGORY
                }
                else ->{
                    return@setOnClickListener
                }
            }
            val direction =
                CategoryDialogFragmentDirections.actionShowCategoryDialog(categoryType)
//
            findNavController().navigate(direction)
//            findNavController().navigate(R.id.action_show_categoryDialog)
        }

        viewModel.messageEvent.subscribe(this, this::showMessage)

        mainViewModel.getPickedExpenseCategory().subscribe(this){tuple->
            tuple?.let {
                viewModel.category(it.k).subscribe(this){category ->
                    categoryId=category.id
                    categoryName.text=category.name
                    categoryIcon.setImageDrawable(viewModel.getIconDrawable(category.iconName))
                    val drawable = categoryColor.categoryColor.drawable.mutate() as GradientDrawable
                    drawable.setColor(category.color)
                }
            }
        }

        mainViewModel.getPickedAccount().subscribe(this) { accountId ->
            accountId?.let {
                viewModel.account(it).subscribe(this){account ->
                    this.accountId=account.id
                    accountName.text=account.name
                    accountIcon.setImageDrawable(viewModel.getIconDrawable(account.iconName))
                    val drawable= accountIcon.drawable
                    drawable.setTint(account.color)
                }
            }
        }

        accountLayout.setOnClickListener{
            findNavController().navigate(R.id.action_show_accountDialog)
        }

        viewModel.operationTypeEvent.subscribe(this){
            val transition = TransitionSet()
                .addTransition(ChangeTransform())
                .addTransition(ChangeClipBounds())
                .addTransition(Fade())
                .addTransition(ChangeBounds())
            transition.interpolator = LinearInterpolator()
            transition.duration = 250L
            TransitionManager.beginDelayedTransition(editOperationRoot, transition)
            totalIncomes.isSelected=false
            expense.isSelected=false
            transfer.isSelected=false
            incomeBg.visibility=View.INVISIBLE
            expenseBg.visibility=View.INVISIBLE
            transferBg.visibility=View.INVISIBLE
            when (it){
                OperationType.INCOME->{
                    totalIncomes.isSelected=true
                    incomeBg.visibility=View.VISIBLE
                }
                OperationType.EXPENSE->{
                    expense.isSelected=true
                    expenseBg.visibility=View.VISIBLE
                }
                OperationType.OUT_TRANSFER,OperationType.IN_TRANSFER->{
                    transfer.isSelected=true
                    transferBg.visibility=View.VISIBLE
                }
            }
        }

        totalIncomes.setOnClickListener {
           viewModel.operationTypeEvent.setValue(OperationType.INCOME)
        }

        expense.setOnClickListener {
            viewModel.operationTypeEvent.setValue(OperationType.EXPENSE)
        }

        transfer.setOnClickListener {
            viewModel.operationTypeEvent.setValue(OperationType.OUT_TRANSFER)
        }

        add.setOnClickListener {
            val amountString=amount.text.toString()
            val noteString=note.text.toString()

            val saved = viewModel.saveOperation(
                accountId,
                amountString,
                noteString,
                categoryId,
                null
            )
            view?.hideKeyboard()
            if (saved) findNavController().navigateUp()
        }

    }


    private fun showMessage(message: EditOperationViewModel.Message) {
        when (message) {
            is EditOperationViewModel.Message.EmptyAccount -> {
                val snackbar = Snackbar.make(editOperationRoot, R.string.editOperationEmptyAccount, Snackbar.LENGTH_SHORT)
                snackbar.show()
            }
            is EditOperationViewModel.Message.EmptyAmount -> {
                val snackbar = Snackbar.make(editOperationRoot, R.string.editOperationEmptyAmount, Snackbar.LENGTH_SHORT)
                snackbar.show()
            }
            is EditOperationViewModel.Message.EmptyCategory -> {
                val snackbar = Snackbar.make(editOperationRoot, R.string.editOperationEmptyCategory, Snackbar.LENGTH_SHORT)
                snackbar.show()
            }
        }
    }


}
