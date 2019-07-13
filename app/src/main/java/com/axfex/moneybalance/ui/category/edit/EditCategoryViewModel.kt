package com.axfex.moneybalance.ui.category.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.axfex.moneybalance.data.source.Repository
import com.axfex.moneybalance.domain.model.category.Category
import com.axfex.moneybalance.domain.model.category.CategoryType
import com.axfex.moneybalance.domain.model.category.ExpenseCategory
import com.axfex.moneybalance.domain.model.category.IncomeCategory
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

    fun category(categoryId: String, type: CategoryType):LiveData<out Category>? {
        return when (type) {
            CategoryType.EXPENSE_CATEGORY -> expenseCategory(categoryId)
            CategoryType.INCOME_CATEGORY -> incomeCategory(categoryId)
        }
    }

    fun saveCategory(
        name: String,
        iconName:String,
        color: Int,
        type: CategoryType,
        id: String? = null
    ): Boolean {

        if (name.isEmpty()) {
            messageEvent.setValue(Message.EmptyName)
            return false
        }

        val categoryId = id ?: UUID.randomUUID().toString()

        when (type) {
            CategoryType.EXPENSE_CATEGORY -> {
                val category = ExpenseCategory(
                    categoryId,
                    name,
                    iconName,
                    color,
                    CategoryType.EXPENSE_CATEGORY
                )
                insertExpenseCategory(category)
            }
            CategoryType.INCOME_CATEGORY -> {
                val category = IncomeCategory(
                    categoryId,
                    name,
                    iconName,
                    color,
                    CategoryType.INCOME_CATEGORY
                )
                insertIncomeCategory(category)
            }
        }

        return true
    }

    fun deleteCategory(categoryId: String, type: CategoryType) {
        when (type) {
            CategoryType.EXPENSE_CATEGORY -> deleteExpenseCategory(categoryId)
            CategoryType.INCOME_CATEGORY -> deleteIncomeCategory(categoryId)
        }
    }

    private fun expenseCategory(categoryId: String) = repo.expenseCategory(categoryId)
    private fun incomeCategory(categoryId: String) = repo.incomeCategory(categoryId)

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

    fun getIconDrawable(iconName: String) = repo.getIconDrawable(iconName)




    fun findIconPosition(findIconName: String): Int? {
        return iconList.value?.indexOfFirst { findIconName == it.name }
//        return iconList.find {findIcon.name == it.name}?.sortOrder
    }

    fun findColorPosition(color: Int): Int? {
        return colorList.indexOfFirst { color == it }
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
