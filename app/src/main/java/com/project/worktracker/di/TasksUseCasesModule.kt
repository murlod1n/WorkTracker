package com.project.worktracker.di

import com.project.domain.repositories.TasksRepository
import com.project.domain.usecases.GetAllTasksUseCase
import com.project.domain.usecases.InsertTaskUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class TasksUseCasesModule {

    @Singleton
    @Provides
    fun provideGetAllTasksUseCase(tasksRepository: TasksRepository) : GetAllTasksUseCase =
        GetAllTasksUseCase(tasksRepository)

    @Singleton
    @Provides
    fun provideInsertTaskUseCase(tasksRepository: TasksRepository): InsertTaskUseCase =
        InsertTaskUseCase(tasksRepository)

}