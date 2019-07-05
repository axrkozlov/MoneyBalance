package com.axfex.moneybalance.ui.category.edit

import android.graphics.drawable.Drawable
import android.util.Log
import androidx.lifecycle.ViewModel;
import com.axfex.moneybalance.data.source.Repository

class EditCategoryViewModel(val repo:Repository) : ViewModel() {
    // TODO: Implement the ViewModel

    fun iconList() = repo.iconList()
    fun colorList() = repo.colorList()

    
    fun dosmth(){
        Log.i("EditCategoryViewModel", "dosmth: ")
        
    }
}
