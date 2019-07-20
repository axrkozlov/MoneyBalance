package com.axfex.moneybalance.ui.add_operation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.axfex.moneybalance.data.source.Repository
import com.axfex.moneybalance.domain.model.category.Category
import com.axfex.moneybalance.domain.model.category.CategoryType
import com.axfex.moneybalance.domain.model.category.ExpenseCategory
import com.axfex.moneybalance.domain.model.category.IncomeCategory
import com.axfex.moneybalance.domain.model.operation.Expense
import com.axfex.moneybalance.ui.category.edit.EditCategoryViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.util.*
import kotlin.coroutines.CoroutineContext

class AddOperationViewModel(val repo:Repository) : ViewModel() , CoroutineScope {

    private val viewModelJob = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + viewModelJob

    fun category(categoryId: String, type: CategoryType): LiveData<out Category>? {
        return when (type) {
            CategoryType.EXPENSE_CATEGORY -> expenseCategory(categoryId)
            CategoryType.INCOME_CATEGORY -> incomeCategory(categoryId)
        }
    }

    fun account(accountId: String) = repo.creditAccount(accountId)
    private fun expenseCategory(categoryId: String) = repo.expenseCategory(categoryId)
    private fun incomeCategory(categoryId: String) = repo.incomeCategory(categoryId)

    fun getIconDrawable(iconName: String) = repo.getIconDrawable(iconName)

    fun saveOperation(
        accountId: String,
        amount: BigDecimal,
        note: String,
        expenceCategoryId:String,
        id: String?
    ): Boolean {

        val id = id ?: UUID.randomUUID().toString()

                val category = Expense(
                    id,
                    accountId,
                    amount,
                    note,
                    expenceCategoryId
                )
                insertExpense(category)

        return true
    }

    private fun insertExpense(expense: Expense) {
        launch(Dispatchers.IO) {
//            repo.insertExpense(expense)
        }
    }

    private fun insertExpenseCategory(category: ExpenseCategory) {

    }
}
