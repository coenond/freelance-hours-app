package com.coen.freelancehours.database.tax

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.coen.freelancehours.model.Tax

@Database(entities = [Tax::class], version = 1, exportSchema = false)
abstract class TaxDatabase : RoomDatabase() {

    abstract fun taxDao(): TaxDAO

    /**
     * Use companion object to make methods public.
     */
    companion object {
        @Volatile
        var taxDatabaseInstance: TaxDatabase? = null

        fun getDatabase(context: Context): TaxDatabase? {
            if (taxDatabaseInstance == null) {
                synchronized(TaxDatabase::class.java) {
                    if (taxDatabaseInstance == null) {
                        taxDatabaseInstance = Room.databaseBuilder(
                                context.applicationContext,
                                TaxDatabase::class.java, "tax_database")
                                .build()
                    }
                }
            }

            return taxDatabaseInstance
        }
    }
}