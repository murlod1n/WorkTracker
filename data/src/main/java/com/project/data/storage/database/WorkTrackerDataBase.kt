package com.project.data.storage.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.project.data.storage.dao.TaskDao
import com.project.data.storage.models.TaskEntity

@Database(entities = [TaskEntity::class], version = 2, exportSchema = false)
abstract class WorkTrackerDataBase: RoomDatabase() {

    abstract fun getTaskDao() : TaskDao

    companion object {
        private var INSTANCE :  WorkTrackerDataBase? = null

        fun getDataBase(context: Context): WorkTrackerDataBase {

            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, WorkTrackerDataBase::class.java, "work_tracker_database.db")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as WorkTrackerDataBase

        }
    }

}