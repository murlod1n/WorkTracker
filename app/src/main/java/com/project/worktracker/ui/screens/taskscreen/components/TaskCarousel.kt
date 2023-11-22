package com.project.worktracker.ui.screens.taskscreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            modifier = Modifier.size(64.dp),

            enabled = currentTaskIndex != 0,
            onClick = {
                if (currentTaskIndex != 0) {
                    currentTaskIndex--
                    setCurrentTask(taskList[currentTaskIndex])
                }
            }
        ) {
            Icon(
                modifier = Modifier.size(40.dp),
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = null,
                tint = if (currentTaskIndex == 0) Color.Transparent else Color.White
            )
        }
        Text(
            taskList[currentTaskIndex].taskTitle, style = TextStyle(
                color = Color.White, fontWeight = FontWeight.Bold, fontSize = 30.sp
            )
        )
        IconButton(
            modifier = Modifier.size(64.dp),
            enabled = currentTaskIndex != taskList.size - 1,
            onClick = {
                if (currentTaskIndex != taskList.size - 1) {
                    currentTaskIndex++
                    setCurrentTask(taskList[currentTaskIndex])
                }
            }
        ) {
            Icon(
                modifier = Modifier.size(40.dp),
                tint = if (currentTaskIndex == taskList.size - 1) Color.Transparent else Color.White,
                imageVector = Icons.Outlined.KeyboardArrowRight,
                contentDescription = null
            )
        }
    }
}