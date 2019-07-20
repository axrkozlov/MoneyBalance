package com.axfex.moneybalance.ui.balance

import android.util.Log
import androidx.lifecycle.ViewModel;
import com.axfex.moneybalance.data.source.Repository

class BalanceViewModel(repo:Repository) : ViewModel() {
    init {
        repo.getAmounts()
    }

}
