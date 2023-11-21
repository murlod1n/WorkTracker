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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.project.worktracker.ui.screens.components.CustomBottomSheet
import com.project.worktracker.ui.screens.taskscreen.components.CustomClock
import com.project.worktracker.ui.screens.taskscreen.components.TaskCarousel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TaskListScreen(state: TaskListState, actions: TaskListActions) {

    var showSheet by remember { mutableStateOf(false) }
    var currentTask by remember {
        mutableIntStateOf(0)
    }

    if (showSheet) {
        CustomBottomSheet(
            insertTask = { actions.insertTask(it) },
            onDismiss = { showSheet = false },
        )
    }

    Column(
        Modifier
            .background(Color.White)
            .padding(16.dp, 16.dp, 16.dp, 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                modifier = Modifier.size(50.dp),
                contentPadding = PaddingValues(1.dp),
                onClick = { actions.onResetWatch() }) {
                Icon(imageVector = Icons.Filled.Edit, contentDescription = null)
            }
            Button(
                modifier = Modifier.size(50.dp),
                contentPadding = PaddingValues(1.dp),
                onClick = { showSheet = true }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            }
        }
        Spacer(modifier = Modifier.height(70.dp))
       if(state.taskList.isNotEmpty()) {
           CustomClock(
               onStartWatch = actions.onStartWatch,
               onPauseWatch = actions.onPauseWatch,
               onResetWatch = actions.onResetWatch,
               formattedTime = state.formattedTime,
               timeCount = state.taskList[currentTask].taskDuration,
               onRestStartWatch = actions.onRestStartWatch,
               onRestPauseWatch = actions.onRestPauseWatch,
               onRestResetWatch = actions.onRestResetWatch,
               restTime = state.restTime,
               time = state.time
           )

           TaskCarousel(taskList = state.taskList, currentTask = currentTask, setCurrentTask = {currentTask = it})
       } else {
           Column() {
               Text(text = "Empty")
           }
       }
    }
}