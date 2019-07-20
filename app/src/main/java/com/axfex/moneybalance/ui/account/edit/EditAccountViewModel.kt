package com.axfex.moneybalance.ui.account.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel;
import com.axfex.moneybalance.data.source.Repository
import com.axfex.moneybalance.domain.model.account.Account
import com.axfex.moneybalance.domain.model.account.CreditAccount
import com.axfex.moneybalance.utils.FreshMutableLiveData
import kotlinx.coroutines.*
import java.math.BigDecimal
import java.util.*
import kotlin.coroutines.CoroutineContext

class EditAccountViewModel(val repo: Repository) : ViewModel(), CoroutineScope {

    private val viewModelJob = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + viewModelJob

    val iconList = repo.iconList()

    val colorList = repo.colorList()

    val messageEvent = FreshMutableLiveData<Message>()

    fun account(accountId: String) = repo.creditAccount(accountId)

    fun saveAccount(
        name: String,
        iconName: String,
        color: Int,
        amount: BigDecimal,
        id: String? = null
    ): Boolean {

        if (name.isEmpty()) {
            messageEvent.setValue(Message.EmptyName)
            return false
        }

        val accountId = id ?: UUID.randomUUID().toString()
        val account = CreditAccount(
            accountId,
            name,
            iconName,
            color,
            amount
        )
        insertCreditAccount(account)


        return true
    }

    fun deleteAccount(accountId: String) {
        deleteCreditAccount(accountId)

    }


    private fun insertCreditAccount(account: CreditAccount) {
        launch(Dispatchers.IO) {
            repo.insertCreditAccount(account)
        }
    }


    private fun deleteCreditAccount(accountId: String) {
        launch(Dispatchers.IO) {
            repo.deleteCreditAccount(accountId)
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
