package com.project.domain.repositories

import com.project.domain.models.Task
import kotlinx.coroutines.flow.Flow

interface TasksRepository {

    fun getAllTasks(): Flow<List<Task>>

    suspend fun insertTask(task: Task)

}