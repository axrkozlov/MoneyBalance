package com.axfex.moneybalance.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.axfex.moneybalance.domain.model.category.*

@Dao
interface CategoriesDao{

    @Query("SELECT * FROM CategoryListView")
    fun categoryList(): LiveData<List<CategoryListView>>

    @Query("Select * FROM category where id = :categoryId")
    fun category(categoryId: String): LiveData<Category>


    @Insert(onConflict = REPLACE)
    fun insertCategory(vararg category: Category)

    @Query("DELETE FROM category where id = :categoryId")
    fun deleteCategory(categoryId: String)

//    @Query("SELECT * FROM CategoryListView WHERE type=0")
//    fun expenseCategoryList(): LiveData<List<CategoryListView>>
//
//    @Query("SELECT * FROM CategoryListView where type=1")
//    fun incomeCategoryList(): LiveData<List<CategoryListView>>


//    @Query("Select * FROM incomeCategory where id = :categoryId")
//    fun incomeCategory(categoryId: String): LiveData<IncomeCategory>


//    @Insert(onConflict = REPLACE)
//    fun insertIncomeCategory(vararg category: IncomeCategory)


}