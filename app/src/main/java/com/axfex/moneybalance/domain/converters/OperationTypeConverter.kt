package com.axfex.moneybalance.domain.converters


import androidx.room.TypeConverter
import com.axfex.moneybalance.domain.model.operation.OperationType
import com.axfex.moneybalance.domain.model.operation.OperationType.*

class OperationTypeConverter {

    @TypeConverter
    fun toOperationType(index: Int): OperationType {
        return when (index) {
            0 -> EXPENSE
            1 -> INCOME
            2 -> IN_TRANSFER
            3 -> OUT_TRANSFER
            else -> throw IllegalArgumentException("can't find category type by index")
        }
    }

    @TypeConverter
    fun toOperationIndex(categoryType: OperationType): Int {
        return when (categoryType) {
            EXPENSE -> EXPENSE.index
            INCOME -> INCOME.index
            IN_TRANSFER -> IN_TRANSFER.index
            OUT_TRANSFER -> OUT_TRANSFER.index
        }
    }
}