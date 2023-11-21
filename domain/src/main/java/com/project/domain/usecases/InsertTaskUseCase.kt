package com.project.domain.usecases

import com.project.domain.models.Task
import com.project.domain.repositories.TasksRepository

class InsertTaskUseCase(private val repository: TasksRepository) {

    suspend operator fun invoke(task: Task) = repository.insertTask(task)

}