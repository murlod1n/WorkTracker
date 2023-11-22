package com.project.worktracker.ui.screens.taskscreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.project.worktracker.ui.screens.taskscreen.components.CustomBottomSheet
import com.project.worktracker.ui.screens.taskscreen.components.CustomClock
import com.project.worktracker.ui.screens.taskscreen.components.TaskCarousel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TaskListScreen(state: TaskListState, actions: TaskListActions) {

    var showSheet by remember { mutableStateOf(false) }
    var isPlay by remember { mutableStateOf(false) }

    if (showSheet) {
        CustomBottomSheet(
            insertTask = { actions.insertTask(it) },
            onDismiss = { showSheet = false },
            taskUI = if (state.currentTask.taskId == 0) null else state.currentTask
        )
    }

    Column(
        Modifier
            .background(Color(27, 27, 27, 255))
            .padding(16.dp, 16.dp, 16.dp, 16.dp), verticalArrangement = Arrangement.SpaceBetween
    ) {


        if (state.taskList.isNotEmpty()) {
            TaskCarousel(
                taskList = state.taskList,
                setCurrentTask = {
                    actions.setCurrentTask(it)
                    actions.onResetWatch()
                    actions.onRestResetWatch()
                    isPlay = false
                })
            Column {
                CustomClock(
                    onStartWatch = actions.onStartWatch,
                    onPauseWatch = actions.onPauseWatch,
                    formattedTime = state.formattedTime,
                    timeCount = state.currentTask.taskDuration,
                    onRestStartWatch = actions.onRestStartWatch,
                    onRestResetWatch = actions.onRestResetWatch,
                    restTime = state.restTime,
                    time = state.time
                )
                Spacer(modifier = Modifier.height(50.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 26.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Button(
                        modifier = Modifier.size(64.dp),
                        contentPadding = PaddingValues(1.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(1, 203, 238, 255)
                        ),
                        onClick = {
                            if (isPlay) {
                                actions.onPauseWatch()
                            } else {
                                actions.onStartWatch()
                            }
                            isPlay = !isPlay
                        }
                    ) {
                        Icon(
                            imageVector = if (isPlay) Icons.Default.Clear else Icons.Filled.PlayArrow,
                            contentDescription = null,
                            tint = Color(29, 29, 29, 255)
                        )
                    }
                    Button(
                        modifier = Modifier.size(64.dp),
                        contentPadding = PaddingValues(1.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(1, 203, 238, 255)
                        ),
                        onClick = {
                            actions.onResetWatch()
                            isPlay = false
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Refresh,
                            contentDescription = null,
                            tint = Color(29, 29, 29, 255)
                        )
                    }
                    Button(
                        modifier = Modifier.size(64.dp),
                        contentPadding = PaddingValues(1.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(1, 203, 238, 255)
                        ),
                        onClick = {
                            showSheet = true
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = null,
                            tint = Color(29, 29, 29, 255)
                        )
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(1, 203, 238, 255)
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.small,
                    contentPadding = PaddingValues(10.dp),
                    onClick = { showSheet = true }) {
                    Text(text = "Add new task", color = Color(29, 29, 29, 255))
                }
            }
        } else {
            Column {
                Text(text = "Empty")
            }
        }
    }
}