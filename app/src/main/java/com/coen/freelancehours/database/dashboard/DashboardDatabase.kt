package com.coen.freelancehours.database.dashboard

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.coen.freelancehours.model.Dashboard

@Database(entities = [Dashboard::class], version = 1, exportSchema = false)
abstract class DashboardDatabase : RoomDatabase() {

    abstract fun dashboardDao(): DashboardDAO

    /**
     * Use companion object to make methods public.
     */
    companion object {
        @Volatile
        var dashboardDatabaseInstance: DashboardDatabase? = null

        fun getDatabase(context: Context): DashboardDatabase? {
            if (dashboardDatabaseInstance == null) {
                synchronized(DashboardDatabase::class.java) {
                    if (dashboardDatabaseInstance == null) {
                        dashboardDatabaseInstance = Room.databaseBuilder(
                                context.applicationContext,
                                DashboardDatabase::class.java, "dashboard_database")
                                .build()
                    }
                }
            }

            return dashboardDatabaseInstance
        }
    }
}