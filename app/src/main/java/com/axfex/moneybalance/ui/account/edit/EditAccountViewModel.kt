package com.axfex.moneybalance.ui.account.edit

import androidx.lifecycle.ViewModel;
import com.axfex.moneybalance.data.source.Repository
import com.axfex.moneybalance.domain.model.account.Account
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

    fun account(accountId: String) = repo.account(accountId)

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
        val account = Account(
            accountId,
            name,
            iconName,
            color,
            amount
        )
        insertAccount(account)


        return true
    }

    fun deleteAccount(accountId: String) {
        launch(Dispatchers.IO) {
            repo.deleteAccount(accountId)
        }
    }


    private fun insertAccount(account: Account) {
        launch(Dispatchers.IO) {
            repo.insertAccount(account)
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
