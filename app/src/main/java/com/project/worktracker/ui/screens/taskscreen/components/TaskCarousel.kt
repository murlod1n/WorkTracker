package com.project.worktracker.ui.screens.taskscreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.KeyboardArrowRight
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
    setCurrentTask: (TaskUI) -> Unit
) {

    var currentTaskIndex by remember {
        mutableIntStateOf(0)
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            enabled = currentTaskIndex != 0,
            onClick = {
                if (currentTaskIndex != 0) {
                    currentTaskIndex--
                    setCurrentTask(taskList[currentTaskIndex])
                }
            }
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = null,
                tint = if (currentTaskIndex == 0) Color.Transparent else Color.Black
            )
        }
        Text(taskList[currentTaskIndex].taskTitle)
        IconButton(
            enabled = currentTaskIndex != taskList.size - 1,
            onClick = {
                if (currentTaskIndex != taskList.size - 1) {
                    currentTaskIndex++
                    setCurrentTask(taskList[currentTaskIndex])
                }
            }
        ) {
            Icon(
                tint = if (currentTaskIndex == taskList.size - 1) Color.Transparent else Color.Black,
                imageVector = Icons.Outlined.KeyboardArrowRight,
                contentDescription = null
            )
        }
    }
}