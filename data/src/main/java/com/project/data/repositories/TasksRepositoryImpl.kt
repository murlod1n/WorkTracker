package com.project.data.repositories

import com.project.data.mappers.toTask
import com.project.data.mappers.toTaskEntity
import com.project.data.storage.dao.TaskDao
import com.project.data.storage.models.TaskEntity
import com.project.domain.models.Task
import com.project.domain.repositories.TasksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TasksRepositoryImpl @Inject constructor(private val taskDao: TaskDao): TasksRepository {

    override fun getAllTasks(): Flow<List<Task>> = flow {
        emit(taskDao.getAllTasks().map { it.toTask() })
    }

    override suspend fun insertTask(task: Task) {
        taskDao.insertTask(task = task.toTaskEntity())
    }

}