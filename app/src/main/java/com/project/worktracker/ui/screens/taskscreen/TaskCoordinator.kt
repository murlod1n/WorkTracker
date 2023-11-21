package com.project.worktracker.ui.screens.taskscreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.worktracker.models.TaskUI

@RequiresApi(Build.VERSION_CODES.O)
class TaskListCoordinator(
    private val viewModel: TaskViewModel
) {

    val state = viewModel.stateFlow

    fun insertTask(task: TaskUI) = viewModel.insertTask(task)

    fun onStartWatch() =  viewModel.onStartWatch()
    fun onPauseWatch() = viewModel.onPauseWatch()
    fun onResetWatch() = viewModel.onResetWatch()

    fun onRestStartWatch() = viewModel.onRestStartWatch()
    fun onRestPauseWatch() = viewModel.onRestPauseWatch()
    fun onRestResetWatch() = viewModel.onRestResetWatch()
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun rememberTaskListCoordinator(
    viewModel: TaskViewModel = hiltViewModel()
): TaskListCoordinator = remember(viewModel) { TaskListCoordinator(viewModel) }