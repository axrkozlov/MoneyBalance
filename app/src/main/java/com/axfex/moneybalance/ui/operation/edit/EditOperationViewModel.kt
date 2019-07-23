package com.axfex.moneybalance.ui.operation.edit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.axfex.moneybalance.data.source.Repository
import com.axfex.moneybalance.domain.model.category.Category
import com.axfex.moneybalance.domain.model.category.CategoryType
import com.axfex.moneybalance.domain.model.operation.Operation
import com.axfex.moneybalance.domain.model.operation.OperationType
import com.axfex.moneybalance.ui.category.edit.EditCategoryViewModel
import com.axfex.moneybalance.utils.FreshMutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.util.*
import kotlin.coroutines.CoroutineContext

class EditOperationViewModel(val repo:Repository) : ViewModel() , CoroutineScope {

    private val viewModelJob = SupervisorJob()
    val messageEvent = FreshMutableLiveData<Message>()
    val operationTypeEvent = MutableLiveData<OperationType>()

    init {
        operationTypeEvent.setValue(OperationType.EXPENSE)
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + viewModelJob


    fun account(accountId: String) = repo.account(accountId)
    fun category(categoryId: String) = repo.category(categoryId)


    fun getIconDrawable(iconName: String) = repo.getIconDrawable(iconName)

    fun saveOperation(
        accountId: String?,
        amountString: String?,
        noteString : String?,
        categoryId:String?,
        operationId: String?
    ): Boolean {

        if (accountId==null || accountId.isEmpty()){
            messageEvent.setValue(Message.EmptyAccount)
            return false
        }
        val amount:BigDecimal

        if (amountString==null || amountString.isEmpty()){
            messageEvent.setValue(Message.EmptyAmount)
            return false
        } else {
            amount=BigDecimal(amountString)
            Log.i("EditOperationViewModel", "saveOperation (line 55): $amount ")
        }

        val note:String
        if (noteString==null || noteString.isEmpty()){
           note="Payment"
        } else {
            note=noteString
        }

        if (categoryId==null || categoryId.isEmpty()){
            messageEvent.setValue(Message.EmptyCategory)
            return false
        }

        val operationType= operationTypeEvent.value ?: return false

        val id = operationId ?: UUID.randomUUID().toString()

                val operation = Operation(
                    id,
                    accountId,
                    amount,
                    note,
                    categoryId,
                    operationType
                )
                insertOperation(operation)
        return true
    }

    private fun insertOperation(operation: Operation) {
        launch(Dispatchers.IO) {
            repo.insertOperation(operation)
        }
    }


    sealed class Message {
        object EmptyCategory : Message()
        object EmptyAccount : Message()
        object EmptyAmount : Message()
    }

//    sealed class ViewState{
//        object ExpenceView:ViewState()
//        object IncomeView:ViewState()
//        object TransferView:ViewState()
//    }
}
