package com.axfex.moneybalance.data.source.local

import android.content.Context
import com.axfex.moneybalance.data.source.OperationsDataSource
import com.axfex.moneybalance.domain.balance.Amount
import com.axfex.moneybalance.domain.balance.Operation


class LocalDataSource(context: Context):OperationsDataSource {

init {

}

    fun addOperation(operation: Operation){

    }

    fun getOperations(){

    }

    fun addAmount(amount: Amount){

    }

    fun getAmounts():List<Amount>?{
return null
    }








}