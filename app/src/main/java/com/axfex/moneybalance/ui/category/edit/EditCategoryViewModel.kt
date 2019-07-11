package com.axfex.moneybalance.ui.category.edit

import androidx.lifecycle.ViewModel;
import com.axfex.moneybalance.data.source.Repository
import com.axfex.moneybalance.domain.category.ExpenseCategory
import com.axfex.moneybalance.domain.category.IncomeCategory
import com.axfex.moneybalance.domain.icon.Icon
import com.axfex.moneybalance.ui.category.CategoryTypesEnum
import com.axfex.moneybalance.utils.FreshMutableLiveData
import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.CoroutineContext

class EditCategoryViewModel(val repo: Repository) : ViewModel(), CoroutineScope {

    private val viewModelJob = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + viewModelJob

    val iconList = repo.iconList()

    val colorList = repo.colorList()

    val messageEvent = FreshMutableLiveData<Message>()

    fun category(categoryId: String, type: CategoryTypesEnum?) {
        when (type) {
            CategoryTypesEnum.EXPENSE_CATEGORY -> expenseCategory(categoryId)
            CategoryTypesEnum.INCOME_CATEGORY -> incomeCategory(categoryId)
            null -> return
        }
    }

    fun saveCategory(
        name: String,
        iconIndex: Int,
        colorIndex: Int,
        type: CategoryTypesEnum?,
        id: String? = null
    ): Boolean {

        if (name.isEmpty()) {
            messageEvent.setValue(Message.EmptyName)
            return false
        }

        val categoryId = id ?: UUID.randomUUID().toString()
        val icon = iconList[iconIndex]
        icon.backgroundColor = colorList[colorIndex]
        when (type) {
            CategoryTypesEnum.EXPENSE_CATEGORY -> {
                val category = ExpenseCategory(
                    categoryId,
                    name,
                    icon
                )
                insertExpenseCategory(category)
            }
            CategoryTypesEnum.INCOME_CATEGORY -> {
                val category = IncomeCategory(
                    categoryId,
                    name,
                    icon
                )
                insertIncomeCategory(category)
            }
            null -> return false
        }

        return true
    }

    fun expenseCategory(categoryId: String) = repo.expenseCategory(categoryId)
    fun incomeCategory(categoryId: String) = repo.incomeCategory(categoryId)

    private fun insertExpenseCategory(category: ExpenseCategory) {
        launch(Dispatchers.IO) {
            repo.insertExpenseCategory(category)
        }
    }

    private fun insertIncomeCategory(category: IncomeCategory) {
        launch(Dispatchers.IO) {
            repo.insertIncomeCategory(category)
        }
    }

    fun deleteCategory(categoryId: String, type: CategoryTypesEnum?) {
        when (type) {
            CategoryTypesEnum.EXPENSE_CATEGORY -> deleteExpenseCategory(categoryId)
            CategoryTypesEnum.INCOME_CATEGORY -> deleteIncomeCategory(categoryId)
            null -> return
        }
    }

    private fun deleteExpenseCategory (categoryId: String) {
        launch(Dispatchers.IO) {
            repo.deleteExpenseCategory(categoryId)
        }
    }

    private fun deleteIncomeCategory (categoryId: String) {
        launch(Dispatchers.IO) {
            repo.deleteIncomeCategory(categoryId)
        }
    }

    @ExperimentalCoroutinesApi
    override fun onCleared() {
        super.onCleared()
        cancel()
    }

    fun getIconDrawable(icon: Icon) = repo.getIconDrawable(icon)




    fun findIconPosition(findIcon: Icon): Int? {
        return iconList.indexOfFirst { findIcon.name == it.name }
//        return iconList.find {findIcon.name == it.name}?.sortOrder
    }

    fun findColorPosition(findIcon: Icon): Int? {
        return colorList.indexOfFirst { findIcon.backgroundColor == it }
//        return iconList.find {findIcon.name == it.name}?.sortOrder
    }

    sealed class ViewState {
        object ExpenseCategories : ViewState()
        object IncomeCategories : ViewState()
    }

    sealed class Message {
        object EmptyName : Message()
    }

}
