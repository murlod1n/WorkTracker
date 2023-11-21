package com.project.worktracker.ui.screens.taskscreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.project.worktracker.models.TaskUI


@Composable
fun TaskCarousel(
    taskList: List<TaskUI>,
    currentTask: Int,
    setCurrentTask: (Int) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            enabled = currentTask != 0,
            onClick = {
                if (currentTask != 0) setCurrentTask(currentTask - 1)
            }) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = null,
                tint = if (currentTask == 0) Color.Transparent else Color.Black
            )
        }
        Text(taskList[currentTask].taskTitle)
        IconButton(
            enabled = currentTask != taskList.size - 1,
            onClick = { if (currentTask != taskList.size - 1) setCurrentTask(currentTask + 1) }
        ) {
            Icon(
                tint = if (currentTask == taskList.size - 1) Color.Transparent else Color.Black,
                imageVector = Icons.Outlined.KeyboardArrowRight,
                contentDescription = null
            )
        }
    }
}