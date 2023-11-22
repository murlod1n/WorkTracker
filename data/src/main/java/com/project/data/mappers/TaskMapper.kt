package com.project.data.mappers

import com.project.data.storage.models.TaskEntity
import com.project.domain.models.Task

fun TaskEntity.toTask() : Task = Task(
    taskId = this.taskId,
    taskTitle = this.taskTitle,
    taskDuration = this.taskDuration,
)

fun Task.toTaskEntity(): TaskEntity = TaskEntity(
    taskId = this.taskId,
    taskTitle = this.taskTitle,
    taskDuration = this.taskDuration,
)