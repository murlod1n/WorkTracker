package com.project.worktracker.mappers

import com.project.data.mappers.toTask
import com.project.domain.models.Task
import com.project.worktracker.models.TaskUI

fun Task.toTaskUI() : TaskUI = TaskUI(
    taskId = this.taskId,
    taskTitle = this.taskTitle,
    taskDuration = this.taskDuration,
    taskDurationProgress = this.taskDurationProgress
)

fun TaskUI.toTask(): Task = Task(
    taskId = this.taskId,
    taskTitle = this.taskTitle,
    taskDuration = this.taskDuration,
    taskDurationProgress = this.taskDurationProgress
)