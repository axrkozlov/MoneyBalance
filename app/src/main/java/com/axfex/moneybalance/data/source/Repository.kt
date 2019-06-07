package com.axfex.moneybalance.data.source

import android.util.Log
import com.axfex.moneybalance.data.source.local.LocalDataSource
import com.axfex.moneybalance.data.source.remote.RemoteDataSource

class Repository(val lds:LocalDataSource, val rds:RemoteDataSource) {
    init {
        Log.i("Repository", "Repository: ")
        getAmounts()

    }


    fun getAmounts(){
        lds.getAmounts()
    }


}