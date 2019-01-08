package com.coen.freelancehours.database.hour

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.*
import com.coen.freelancehours.model.Hour

@Dao
interface HourDAO {

    @Insert
    fun insert(hour: Hour)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(hours: List<Hour>)

    @Delete
    fun delete(hour: Hour)

    @Update
    fun update(hour: Hour)

    @Query("DELETE FROM Hours")
    fun deleteAll()

    @Query("SELECT * from Hours")
    fun getAll(): LiveData<List<Hour>>
}