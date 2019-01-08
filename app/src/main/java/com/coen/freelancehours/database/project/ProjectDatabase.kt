package com.coen.freelancehours.database.project

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.coen.freelancehours.model.Project

@Database(entities = [Project::class], version = 1, exportSchema = false)
abstract class ProjectDatabase : RoomDatabase() {

    abstract fun projectDao(): ProjectDAO

    /**
     * Use companion object to make methods public.
     */
    companion object {
        @Volatile
        var projectDatabaseInstance: ProjectDatabase? = null

        fun getDatabase(context: Context): ProjectDatabase? {
            if (projectDatabaseInstance == null) {
                synchronized(ProjectDatabase::class.java) {
                    if (projectDatabaseInstance == null) {
                        projectDatabaseInstance = Room.databaseBuilder(
                                context.applicationContext,
                                ProjectDatabase::class.java, "project_database")
                                .build()
                    }
                }
            }

            return projectDatabaseInstance
        }
    }
}