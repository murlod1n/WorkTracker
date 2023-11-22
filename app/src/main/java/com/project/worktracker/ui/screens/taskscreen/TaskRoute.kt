package com.project.worktracker.ui.screens.taskscreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TaskListRoute(
    taskListCoordinator: TaskListCoordinator = rememberTaskListCoordinator()
) {

    val state = taskListCoordinator.state.collectAsState()
    val actions = rememberTaskListAction(taskListCoordinator = taskListCoordinator)

    TaskListScreen(state = state.value, actions = actions)

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun rememberTaskListAction(taskListCoordinator: TaskListCoordinator) : TaskListActions =
    remember(taskListCoordinator) {
        TaskListActions(
            insertTask = taskListCoordinator::insertTask,
            onStartWatch = taskListCoordinator::onStartWatch,
            onPauseWatch = taskListCoordinator::onPauseWatch,
            onResetWatch = taskListCoordinator::onResetWatch,
            onRestPauseWatch = taskListCoordinator::onRestPauseWatch,
            onRestStartWatch = taskListCoordinator::onRestStartWatch,
            onRestResetWatch = taskListCoordinator::onRestResetWatch,
            setCurrentTask = taskListCoordinator::setCurrentTask
        )
    }