package com.project.worktracker.di

import android.app.Application
import com.project.data.storage.dao.TaskDao
import com.project.data.storage.database.WorkTrackerDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Singleton
    @Provides
    fun provideWorkTrackerAppDataBase(app: Application): WorkTrackerDataBase =
        WorkTrackerDataBase.getDataBase(context = app)


    @Singleton
    @Provides
    fun provideTaskDao(workTrackerDataBase: WorkTrackerDataBase): TaskDao = workTrackerDataBase.getTaskDao()

}