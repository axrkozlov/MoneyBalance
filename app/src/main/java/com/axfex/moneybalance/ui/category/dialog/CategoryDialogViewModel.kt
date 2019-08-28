package com.axfex.moneybalance.ui.category.dialog

import androidx.lifecycle.ViewModel;
import com.axfex.moneybalance.data.source.Repository
import java.util.*

class CategoryDialogViewModel(val repo: Repository) : ViewModel() {
    fun categoryList()=repo.categoryList()

    fun getIconDrawable(iconName: String) = repo.getIconDrawable(iconName)

    fun test(){
        val mutableMap= mutableMapOf("one".to(1) )
        mutableMap["one"]=2
        val map:Map<String,Int> = mutableMap
        val hashmap= hashMapOf("two" to 2)
        mutableMap.put("two",3)
        hashmap["three"]=3
        val hashmap1= hashMapOf(ABC(0,false) to 2)
        val abc=ABC(0,false)
        abc.hashCode()
        val queue =ArrayDeque<String>()
        queue.add("asd")
    }

    class ABC(var p1:Int,var p2:Boolean){

         fun somefun(){

        }

    }

}
