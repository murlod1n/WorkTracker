package com.project.worktracker.models

data class TaskUI(
    val taskId: Int,
    val taskTitle: String,
    val taskDuration: Long,
    val taskDurationProgress: Long
)