package com.project.worktracker.ui.screens.taskscreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.project.worktracker.models.TaskUI
import java.text.SimpleDateFormat
import java.util.Date


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomBottomSheet(
    onDismiss: () -> Unit,
    insertTask: (TaskUI) -> Unit = {},
    taskUI: TaskUI? = null
) {

    val modalBottomSheetState = rememberModalBottomSheetState()

    var taskTitle by remember { mutableStateOf(if(taskUI != null) taskUI.taskTitle else "") }

    val state = rememberTimePickerState(
        is24Hour = true,
        initialHour = if(taskUI != null) SimpleDateFormat("hh").format(Date(taskUI.taskDuration)).toInt() - 3 else 0,
        initialMinute = if(taskUI != null) SimpleDateFormat("mm").format(Date(taskUI.taskDuration)).toInt() else 0,
    )


    ModalBottomSheet(
        containerColor = Color.White,
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 62.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(bottom = 16.dp),
                text = "Add new task",
                style = MaterialTheme.typography.titleLarge
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large,
                value = taskTitle,
                onValueChange = { taskTitle = it },
                label = { Text(text = "Enter a project name") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                )
            )

            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Task duration")
            Spacer(modifier = Modifier.height(20.dp))

            TimeInput(state = state)
            Row(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    shape = MaterialTheme.shapes.medium,
                    onClick = { onDismiss() }
                ) {
                    Text(text = "Cancel")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    modifier = Modifier.weight(1f),
                    shape = MaterialTheme.shapes.medium,
                    onClick = {

                        val time = ((state.hour * 60) + state.minute) * 60000L

                        insertTask(
                            TaskUI(
                                taskId = taskUI?.taskId ?: 0,
                                taskTitle = taskTitle,
                                taskDuration = time
                            )
                        )
                        onDismiss()
                    }
                ) {
                    Text(text = "Save")
                }
            }
        }
    }
}