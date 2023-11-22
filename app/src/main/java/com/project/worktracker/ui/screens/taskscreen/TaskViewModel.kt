package com.project.worktracker.ui.screens.taskscreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.domain.usecases.GetAllTasksUseCase
import com.project.domain.usecases.InsertTaskUseCase
import com.project.worktracker.mappers.toTask
import com.project.worktracker.mappers.toTaskUI
import com.project.worktracker.models.TaskUI
import com.project.worktracker.utils.StopWatchUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val insertTaskUseCase: InsertTaskUseCase
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<TaskListState> = MutableStateFlow(TaskListState())
    val stateFlow = _stateFlow.asStateFlow()

    private var timeMillis = 0L
    private var restTimeMillis = 0L

    init {
        viewModelScope.launch {
            getAllTasksUseCase().collect { tasks ->
                _stateFlow.value = _stateFlow.value.copy(
                    taskList = tasks.map { task -> task.toTaskUI() }
                )
            }
            if (_stateFlow.value.taskList.isNotEmpty()) {
                setCurrentTask(_stateFlow.value.taskList[0])
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onStartWatch() {
        if (_stateFlow.value.isActive) return

        _stateFlow.value.coroutineScope.launch {
            _stateFlow.value = _stateFlow.value.copy(lastTimestamp = System.currentTimeMillis())
            _stateFlow.value = _stateFlow.value.copy(isActive = true)
            while (_stateFlow.value.isActive) {
                delay(10L)
                timeMillis += System.currentTimeMillis() - _stateFlow.value.lastTimestamp
                _stateFlow.value = _stateFlow.value.copy(lastTimestamp = System.currentTimeMillis())

                _stateFlow.value = _stateFlow.value.copy(
                    formattedTime = StopWatchUtils.formatTime(timeMillis),
                    time = timeMillis
                )

            }
        }
    }

    fun onPauseWatch() {
        _stateFlow.value = _stateFlow.value.copy(isActive = false)
    }

    fun onResetWatch() {
        _stateFlow.value.coroutineScope.cancel()
        _stateFlow.value = _stateFlow.value.copy(
            formattedTime = "00:00:00",
            coroutineScope = CoroutineScope(Dispatchers.Main),
            isActive = false,
            lastTimestamp = 0L,
            time = 0L
        )
        timeMillis = 0L
    }

    fun onRestStartWatch() {
        if (_stateFlow.value.restIsActive) return

        _stateFlow.value.restCoroutineScope.launch {
            _stateFlow.value = _stateFlow.value.copy(restLastTimestamp = System.currentTimeMillis())
            _stateFlow.value = _stateFlow.value.copy(restIsActive = true)
            while (_stateFlow.value.restIsActive) {
                delay(10L)
                restTimeMillis += System.currentTimeMillis() - _stateFlow.value.restLastTimestamp
                _stateFlow.value =
                    _stateFlow.value.copy(restLastTimestamp = System.currentTimeMillis())

                _stateFlow.value = _stateFlow.value.copy(
                    restFormattedTime = StopWatchUtils.formatTime(restTimeMillis),
                    restTime = restTimeMillis
                )

            }
        }
    }

    fun setCurrentTask(task: TaskUI) {
        _stateFlow.value = _stateFlow.value.copy(
            currentTask = task
        )
    }

    fun onRestPauseWatch() {
        _stateFlow.value = _stateFlow.value.copy(restIsActive = false)
    }

    fun onRestResetWatch() {
        _stateFlow.value.restCoroutineScope.cancel()
        _stateFlow.value = _stateFlow.value.copy(
            restFormattedTime = "00:00:00",
            restCoroutineScope = CoroutineScope(Dispatchers.Main),
            restIsActive = false,
            restTime = 0L,
            restLastTimestamp = 0L
        )
        restTimeMillis = 0L
    }

    fun insertTask(taskUI: TaskUI) {
        viewModelScope.launch {
            insertTaskUseCase(taskUI.toTask())
            getAllTasksUseCase().collect { tasks ->
                _stateFlow.value = _stateFlow.value.copy(
                    taskList = tasks.map { task -> task.toTaskUI() }
                )
            }
        }
    }


}