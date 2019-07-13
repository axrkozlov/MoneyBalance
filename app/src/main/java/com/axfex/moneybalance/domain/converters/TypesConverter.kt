package com.axfex.moneybalance.domain.converters


import androidx.room.TypeConverter
import com.axfex.moneybalance.domain.model.category.CategoryType

class TypesConverter {
    @TypeConverter
    fun categoryType(index: Int): CategoryType {
        return when (index) {
            0 -> CategoryType.EXPENSE_CATEGORY
            1 -> CategoryType.INCOME_CATEGORY
            else -> throw IllegalArgumentException("can't find category type by index")
        }
    }
        @TypeConverter
        fun categoryType(categoryType: CategoryType): Int {
            return when (categoryType) {
                CategoryType.EXPENSE_CATEGORY -> CategoryType.EXPENSE_CATEGORY.index
                CategoryType.INCOME_CATEGORY -> CategoryType.INCOME_CATEGORY.index
            }
        }
    }