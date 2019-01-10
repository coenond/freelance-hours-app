package com.coen.freelancehours.database.dashboard

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.coen.freelancehours.model.Dashboard

@Dao
interface DashboardDAO {

    @Query("SELECT * FROM dashboards WHERE id = :id")
    fun get(id: Int): LiveData<Dashboard>

    @Update
    fun update(dashboard: Dashboard)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dashboard: Dashboard)

}