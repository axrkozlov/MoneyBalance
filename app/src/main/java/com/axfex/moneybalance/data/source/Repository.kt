package com.axfex.moneybalance.data.source

import android.graphics.drawable.Drawable
import android.util.Log
import com.axfex.moneybalance.data.source.local.LocalDataSource
import com.axfex.moneybalance.data.source.remote.RemoteDataSource

class Repository(val lds:LocalDataSource, val rds:RemoteDataSource, val iconsManager: IconsManager) {
    init {
        Log.i("Repository", "Repository: ")
        getAmounts()

    }


    fun getAmounts(){
        lds.getAmounts()
    }

    fun iconList() = iconsManager.icons
    fun colorList() = iconsManager.colors


}