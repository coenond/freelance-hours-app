package com.coen.freelancehours.database.hour

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.coen.freelancehours.model.Hour

@Database(entities = [Hour::class], version = 1, exportSchema = false)
abstract class HourDatabase : RoomDatabase() {

    abstract fun hourDao(): HourDAO

    /**
     * Use companion object to make methods public.
     */
    companion object {
        @Volatile
        var hourDatabaseInstance: HourDatabase? = null

        fun getDatabase(context: Context): HourDatabase? {
            if (hourDatabaseInstance == null) {
                synchronized(HourDatabase::class.java) {
                    if (hourDatabaseInstance == null) {
                        hourDatabaseInstance = Room.databaseBuilder(
                                context.applicationContext,
                                HourDatabase::class.java, "hour_database")
                                .build()
                    }
                }
            }

            return hourDatabaseInstance
        }
    }
}