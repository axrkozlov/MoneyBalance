package com.axfex.moneybalance.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.axfex.moneybalance.domain.model.category.CategoryType

class MainViewModel: ViewModel() {

    private val pickExpenseCategoryIdEventSource: MutableLiveData<Tuple<String,CategoryType>?> = MutableLiveData()

    class Tuple<K,V>(val k:K,val v:V)

    fun getPickedExpenseCategory():  LiveData<Tuple<String,CategoryType>?> {
        pickExpenseCategoryIdEventSource.value=null
        return pickExpenseCategoryIdEventSource
    }

    fun pickExpenseCategory(id:String,type:CategoryType){
        pickExpenseCategoryIdEventSource.value=Tuple(id,type)
    }


    private val pickAccountIdEventSource: MutableLiveData<String?> = MutableLiveData()

    fun getPickedAccount():  LiveData<String?> {
        pickExpenseCategoryIdEventSource.value=null
        return pickAccountIdEventSource
    }

    fun pickAccount(id:String){
        pickAccountIdEventSource.value=id
    }
}