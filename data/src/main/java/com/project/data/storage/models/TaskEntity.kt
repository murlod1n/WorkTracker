package com.project.data.storage.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "task_id") val taskId: Int,
    @ColumnInfo(name = "task_title") val taskTitle: String,
    @ColumnInfo(name = "task_duration") val taskDuration: Long,
)