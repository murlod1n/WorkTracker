package com.project.domain.usecases

import com.project.domain.models.Task
import com.project.domain.repositories.TasksRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetAllTasksUseCase(private val tasksRepository: TasksRepository) {

    operator fun invoke(): Flow<List<Task>> = tasksRepository.getAllTasks()

}