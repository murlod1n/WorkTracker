package com.project.worktracker.ui.screens.taskscreen.components


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomClock(
    formattedTime: String,
    timeCount: Long,
    time: Long,
    onStartWatch: () -> Unit,
    onPauseWatch: () -> Unit,
    onRestStartWatch: () -> Unit,
    onRestResetWatch: () -> Unit,
    restTime: Long
) {

    var counter by remember {
        mutableIntStateOf(0)
    }
    var lastTime by remember {
        mutableLongStateOf(0L)
    }

    val firstArc =
        if (time > 0L && time < (timeCount / 2) && ((time.toFloat() / timeCount) * 360f) < 168f)
            (time.toFloat() / timeCount) * 360f
        else
            if (time > 0) {
                168f
            } else
                0f

    val secondArc =
        if (time > (timeCount / 2) && time < ((timeCount / 2) + (timeCount / 4))
            && (((time.toFloat() / timeCount) - 0.5f) * 360f) <= 74f
        )
            ((time.toFloat() / timeCount) - 0.5f) * 360f
        else
            75f


    val thirdArc =
        if (time > ((timeCount / 2) + (timeCount / 4)) && time < ((timeCount / 2) + (timeCount / 4) + (timeCount / 8))
            && (((time.toFloat() / timeCount) - 0.75f) * 360f) <= 29f
        )
            ((time.toFloat() / timeCount) - 0.75f) * 360f
        else
            29f

    val fourthArc =
        if (time > ((timeCount / 2) + (timeCount / 4) + (timeCount / 8))
            && (((time.toFloat() / timeCount) - 0.88f) * 360f) <= 29f
        )
            ((time.toFloat() / timeCount) - 0.88f) * 360f
        else
            29f

    LaunchedEffect(time) {
        if (timeCount < time) {
            onPauseWatch()
        } else if (((timeCount / 2) + ((timeCount / 2) / 2) + (((timeCount / 2) / 2) / 2) < time) && (counter == 2)) {
            onRestStartWatch()
            onPauseWatch()
        } else if ((((timeCount / 2) + ((timeCount / 2) / 2)) < time) && (counter == 1)) {
            onRestStartWatch()
            onPauseWatch()
        } else if (((timeCount / 2) < time) && (counter == 0)) {
            onRestStartWatch()
            onPauseWatch()
        }
    }

    LaunchedEffect(restTime) {
        if (restTime > 10000L) {
            counter++

            onRestResetWatch()
            onStartWatch()
        }
    }

    val stroke = 8.dp
    val color = Color.Cyan

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.size(280.dp)) {
            val innerRadius = (size.minDimension - stroke.toPx()) / 2
            val innerRadiusSecond = (size.minDimension - stroke.toPx()) / 2.4f
            val halfSize = size / 2.0f
            val topLeft = Offset(
                x = halfSize.width - innerRadius,
                y = halfSize.height - innerRadius
            )
            val topLeftSecond = Offset(
                x = halfSize.width - innerRadiusSecond,
                y = halfSize.height - innerRadiusSecond
            )
            val size = Size(
                width = innerRadius * 2,
                height = innerRadius * 2
            )
            val sizeSecond = Size(
                width = innerRadiusSecond * 2,
                height = innerRadiusSecond * 2
            )
            val startAngle = -90f

            drawArc(
                color = Color(163, 157, 157, 100),
                startAngle = startAngle,
                sweepAngle = 360f,
                topLeft = topLeftSecond,
                size = sizeSecond,
                useCenter = false,
                style = Stroke(width = 16.dp.toPx())
            )
            if (restTime > 0L) {
                drawArc(
                    color = Color.Green,
                    startAngle = startAngle,
                    sweepAngle = (restTime.toFloat() / 10000L) * 360f,
                    topLeft = topLeftSecond,
                    size = sizeSecond,
                    useCenter = false,
                    style = Stroke(width = stroke.toPx(), cap = StrokeCap.Round)
                )
            }
            drawArc(
                color = Color(163, 157, 157, 100),
                startAngle = startAngle+5f,
                sweepAngle = 168f,
                topLeft = topLeft,
                size = size,
                useCenter = false,
                style = Stroke(width = 16.dp.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = if (counter > 0) Color.Green else Color(163, 157, 157, 100),
                startAngle = 91f,
                sweepAngle = 1f,
                topLeft = topLeft,
                size = size,
                useCenter = false,
                style = Stroke(width = 10.dp.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = Color(163, 157, 157, 100),
                startAngle = 100f,
                sweepAngle = 73f,
                topLeft = topLeft,
                size = size,
                useCenter = false,
                style = Stroke(width = 16.dp.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = if (counter > 1) Color.Green else Color(163, 157, 157, 100),
                startAngle = 181f,
                sweepAngle = 1f,
                topLeft = topLeft,
                size = size,
                useCenter = false,
                style = Stroke(width = stroke.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = Color(163, 157, 157, 100),
                startAngle = 190f,
                sweepAngle = 28f,
                topLeft = topLeft,
                size = size,
                useCenter = false,
                style = Stroke(width = 16.dp.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = if (counter > 2) Color.Green else Color(163, 157, 157, 100),
                startAngle = 226f,
                sweepAngle = 1f,
                topLeft = topLeft,
                size = size,
                useCenter = false,
                style = Stroke(width = stroke.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = Color(163, 157, 157, 100),
                startAngle = 235f,
                sweepAngle = 27f,
                topLeft = topLeft,
                size = size,
                useCenter = false,
                style = Stroke(width = 16.dp.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = color,
                startAngle = startAngle+5f,
                sweepAngle = firstArc,
                topLeft = topLeft,
                size = size,
                useCenter = false,
                style = Stroke(width = stroke.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = color,
                startAngle = startAngle + 190f,
                sweepAngle = if(time > (timeCount / 2) && counter > 0) secondArc else 0F,
                topLeft = topLeft,
                size = size,
                useCenter = false,
                style = Stroke(width = stroke.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = color,
                startAngle = startAngle + 280f,
                sweepAngle = if(time > ((timeCount / 2) + (timeCount/4))  && counter > 1) thirdArc else 0F,
                topLeft = topLeft,
                size = size,
                useCenter = false,
                style = Stroke(width = stroke.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = color,
                startAngle = startAngle + 325f,
                sweepAngle = if(time > ((timeCount / 2) + (timeCount/4) + (timeCount / 8))  && counter > 2) fourthArc else 0F,
                topLeft = topLeft,
                size = size,
                useCenter = false,
                style = Stroke(width = stroke.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            text = formattedTime, style = TextStyle(
                color = Color.White,
                fontSize = 34.sp, fontWeight = FontWeight.Black
            )
        )
    }


}