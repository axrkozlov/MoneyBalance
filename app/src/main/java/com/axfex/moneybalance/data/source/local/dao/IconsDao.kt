package com.axfex.moneybalance.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.axfex.moneybalance.domain.model.icon.Icon

@Dao
interface IconsDao{
    @Query("SELECT * FROM icon ORDER BY sortOrder")
    fun iconList(): LiveData<List<Icon>>

    @Query("SELECT * FROM icon WHERE name=:name")
    fun icon(name:String): Icon

    @Insert(onConflict = REPLACE)
    fun insertIcons(vararg icons: Icon)

}