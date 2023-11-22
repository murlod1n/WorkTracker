package com.project.worktracker.ui.screens.taskscreen

import android.os.Build
import androidx.annotation.RequiresApi
import com.project.worktracker.models.TaskUI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.text.SimpleDateFormat


@RequiresApi(Build.VERSION_CODES.O)
data class TaskListState constructor(
    val taskList: List<TaskUI> = listOf(),
    val currentTask: TaskUI = TaskUI(),

    val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main),
    val isActive: Boolean = false,
    val lastTimestamp: Long = 0L,
    val formattedTime: String = "00:00:00",
    var time: Long = 0L,
    val timeCount: Long = SimpleDateFormat("HH:mm").parse("00:00").time,

    val restCoroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main),
    val restIsActive: Boolean = false,
    val restLastTimestamp: Long = 0L,
    val restFormattedTime: String = "00:00:00",
    val restTime: Long = 0L,
    val restTimeCount: Long = 600000L
)

data class TaskListActions(
    val insertTask: (TaskUI) -> Unit,
    val onStartWatch: () -> Unit,
    val onPauseWatch: () -> Unit,
    val onResetWatch: () -> Unit,

    val onRestStartWatch: () -> Unit,
    val onRestPauseWatch: () -> Unit,
    val onRestResetWatch: () -> Unit,

    val setCurrentTask: (TaskUI) -> Unit
)