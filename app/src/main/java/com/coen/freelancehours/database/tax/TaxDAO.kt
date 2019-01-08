package com.coen.freelancehours.database.tax

import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.*
import com.coen.freelancehours.model.Tax

@Dao
interface TaxDAO {

    @Insert
    fun insert(tax: Tax)

    @Delete
    fun delete(tax: Tax)

    @Update
    fun update(tax: Tax)

    @Query("DELETE FROM Taxes")
    fun deleteAll()

    @Query("SELECT * from Taxes")
    fun getAll(): List<Tax>
}