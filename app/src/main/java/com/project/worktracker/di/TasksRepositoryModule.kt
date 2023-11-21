package com.project.worktracker.di


import com.project.data.repositories.TasksRepositoryImpl
import com.project.data.storage.dao.TaskDao
import com.project.domain.repositories.TasksRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TasksRepositoryModule {

    @Singleton
    @Provides
    fun provideTasksRepository(taskDao: TaskDao) : TasksRepository =
        TasksRepositoryImpl(taskDao)

}