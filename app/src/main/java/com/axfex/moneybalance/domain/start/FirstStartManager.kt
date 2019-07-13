package com.axfex.moneybalance.domain.start

import android.util.Log
import com.axfex.moneybalance.data.source.local.LocalDataSource
import com.axfex.moneybalance.domain.model.icon.Icon
import com.axfex.moneybalance.domain.model.icon.IconsManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FirstStartManager(val iconsManager: IconsManager, val lds: LocalDataSource) {

    fun populateDb(){
        GlobalScope.launch(Dispatchers.IO) {
            lds.insertIcons(*iconsManager.iconList().toTypedArray())
        }

    }

}