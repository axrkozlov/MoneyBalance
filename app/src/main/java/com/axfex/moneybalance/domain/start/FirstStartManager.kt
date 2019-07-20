package com.axfex.moneybalance.domain.start

import com.axfex.moneybalance.data.source.local.LocalDataSource
import com.axfex.moneybalance.domain.model.account.CreditAccount
import com.axfex.moneybalance.domain.model.category.CategoryType
import com.axfex.moneybalance.domain.model.category.ExpenseCategory
import com.axfex.moneybalance.domain.model.category.IncomeCategory
import com.axfex.moneybalance.domain.model.icon.IconsManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.util.*

class FirstStartManager(val iconsManager: IconsManager, val lds: LocalDataSource) {

    fun populateDb() {
        GlobalScope.launch(Dispatchers.IO) {
            populateIcons()
            populateCategories()
            populateAccounts()
        }

    }

    private fun populateIcons() {
        lds.insertIcons(*iconsManager.iconList().toTypedArray())
    }

    private fun populateCategories() {
        val expenseCategories=ArrayList<ExpenseCategory>()
        var categoryId = UUID.randomUUID().toString()
        var name = "Grocery"
        var iconName = "icon27.xml"
        var color = iconsManager.colorList()[0]
        var category = ExpenseCategory(
            categoryId,
            name,
            iconName,
            color,
            CategoryType.EXPENSE_CATEGORY
        )
        expenseCategories.add(category)


        categoryId = UUID.randomUUID().toString()
        name = "Auto"
        iconName = "icon211.xml"
        color = iconsManager.colorList()[1]
        category = ExpenseCategory(
            categoryId,
            name,
            iconName,
            color,
            CategoryType.EXPENSE_CATEGORY
        )
        expenseCategories.add(category)
        categoryId = UUID.randomUUID().toString()
        name = "Voyage"
        iconName = "icon181.xml"
        color = iconsManager.colorList()[2]
        category = ExpenseCategory(
            categoryId,
            name,
            iconName,
            color,
            CategoryType.EXPENSE_CATEGORY
        )
        expenseCategories.add(category)
        categoryId = UUID.randomUUID().toString()
        name = "Gifts"
        iconName = "icon154.xml"
        color = iconsManager.colorList()[3]
        category = ExpenseCategory(
            categoryId,
            name,
            iconName,
            color,
            CategoryType.EXPENSE_CATEGORY
        )
        expenseCategories.add(category)
        categoryId = UUID.randomUUID().toString()
        name = "Health"
        iconName = "icon65.xml"
        color = iconsManager.colorList()[4]
        category = ExpenseCategory(
            categoryId,
            name,
            iconName,
            color,
            CategoryType.EXPENSE_CATEGORY
        )
        expenseCategories.add(category)
        categoryId = UUID.randomUUID().toString()
        name = "Education"
        iconName = "icon118.xml"
        color = iconsManager.colorList()[5]
        category = ExpenseCategory(
            categoryId,
            name,
            iconName,
            color,
            CategoryType.EXPENSE_CATEGORY
        )
        expenseCategories.add(category)
        categoryId = UUID.randomUUID().toString()
        name = "Fun"
        iconName = "icon207.xml"
        color = iconsManager.colorList()[2]
        category = ExpenseCategory(
            categoryId,
            name,
            iconName,
            color,
            CategoryType.EXPENSE_CATEGORY
        )
        expenseCategories.add(category)
        categoryId = UUID.randomUUID().toString()
        name = "Other"
        iconName = "icon13.xml"
        color = iconsManager.colorList()[6]
        category = ExpenseCategory(
            categoryId,
            name,
            iconName,
            color,
            CategoryType.EXPENSE_CATEGORY
        )
        expenseCategories.add(category)

        lds.insertExpenseCategory(*expenseCategories.toTypedArray())

        val incomeCategories=ArrayList<IncomeCategory>()
        categoryId = UUID.randomUUID().toString()
        name = "Salary"
        iconName = "icon41.xml"
        color = iconsManager.colorList()[7]
        var category2 = IncomeCategory(
            categoryId,
            name,
            iconName,
            color,
            CategoryType.INCOME_CATEGORY
        )
        incomeCategories.add(category2)

        categoryId = UUID.randomUUID().toString()
        name = "Allowance"
        iconName = "icon5.xml"
        color = iconsManager.colorList()[8]
        category2 = IncomeCategory(
            categoryId,
            name,
            iconName,
            color,
            CategoryType.INCOME_CATEGORY
        )
        incomeCategories.add(category2)

        categoryId = UUID.randomUUID().toString()
        name = "Bonus"
        iconName = "icon318.xml"
        color = iconsManager.colorList()[9]
        category2 = IncomeCategory(
            categoryId,
            name,
            iconName,
            color,
            CategoryType.INCOME_CATEGORY
        )
        incomeCategories.add(category2)

        categoryId = UUID.randomUUID().toString()
        name = "Other"
        iconName = "icon376.xml"
        color = iconsManager.colorList()[10]
        category2 = IncomeCategory(
            categoryId,
            name,
            iconName,
            color,
            CategoryType.INCOME_CATEGORY
        )
        incomeCategories.add(category2)
        lds.insertIncomeCategory(*incomeCategories.toTypedArray())
    }


    private fun populateAccounts() {

        val creditAccounts=ArrayList<CreditAccount>()
        var accountId = UUID.randomUUID().toString()
        var name = "Cash"
        var iconName = "icon165.xml"
        var color = iconsManager.colorList()[9]
        var amount = BigDecimal(20.20)
        var account = CreditAccount(
            accountId,
            name,
            iconName,
            color,
            amount
        )
        creditAccounts.add(account)

         accountId = UUID.randomUUID().toString()
         name = "Credit Card"
         iconName = "icon168.xml"
         color = iconsManager.colorList()[2]
         amount = BigDecimal(20.20)
         account = CreditAccount(
            accountId,
            name,
            iconName,
            color,
            amount
        )
        creditAccounts.add(account)

        lds.insertCreditAccount(*creditAccounts.toTypedArray())
    }



}