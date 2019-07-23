package com.axfex.moneybalance.ui.category.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.axfex.moneybalance.data.source.Repository
import com.axfex.moneybalance.domain.model.category.Category
import com.axfex.moneybalance.domain.model.category.CategoryType
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

    fun category(categoryId: String, type: CategoryType): LiveData<out Category>? {
        return when (type) {
            CategoryType.EXPENSE_CATEGORY -> category(categoryId)
            CategoryType.INCOME_CATEGORY -> category(categoryId)
        }
    }

    fun saveCategory(
        name: String,
        iconName: String,
        color: Int,
        type: CategoryType,
        id: String? = null
    ): Boolean {

        if (name.isEmpty()) {
            messageEvent.setValue(Message.EmptyName)
            return false
        }

        val categoryId = id ?: UUID.randomUUID().toString()

        val category = Category(
            categoryId,
            name,
            iconName,
            color,
            type
        )

        insertCategory(category)

        return true
    }

    fun deleteCategory(categoryId: String, type: CategoryType) {
        launch(Dispatchers.IO) {
            repo.deleteCategory(categoryId)
        }
    }

    private fun category(categoryId: String) = repo.category(categoryId)

    private fun insertCategory(category: Category) {
        launch(Dispatchers.IO) {
            repo.insertCategory(category)
        }
    }



    @ExperimentalCoroutinesApi
    override fun onCleared() {
        super.onCleared()
        cancel()
    }

    fun getIconDrawable(iconName: String) = repo.getIconDrawable(iconName)

    sealed class Message {
        object EmptyName : Message()
    }

}
