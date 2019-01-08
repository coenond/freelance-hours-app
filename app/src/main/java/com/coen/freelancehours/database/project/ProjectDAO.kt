package com.coen.freelancehours.database.project

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.*
import com.coen.freelancehours.model.Project

@Dao
interface ProjectDAO {

    @Insert
    fun insert(project: Project)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(projects: List<Project>)

    @Delete
    fun delete(project: Project)

    @Update
    fun update(project: Project)

    @Query("DELETE FROM Projects")
    fun deleteAll()

    @Query("SELECT * from Projects")
    fun getAll(): LiveData<List<Project>>
}