package com.project.worktracker.ui.screens.taskscreen.components

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.DecimalFormat
import kotlin.math.floor

@Composable
fun CustomClock(
    formattedTime: String,
    timeCount: Long,
    time: Long,
    onStartWatch: () -> Unit,
    onPauseWatch: () -> Unit,
    onResetWatch: () -> Unit,
    onRestStartWatch: () -> Unit,
    onRestPauseWatch: () -> Unit,
    onRestResetWatch: () -> Unit,
    restTime: Long
) {

    var isPlay by remember {
        mutableStateOf(false)
    }
    var counter by remember {
        mutableIntStateOf(0)
    }

    LaunchedEffect(time) {
        if(timeCount < time) {
            onPauseWatch()
        } else if(((timeCount / 2) + ((timeCount / 2) / 2) + (((timeCount / 2) / 2) / 2) < time) && (counter == 2)) {
            counter++
            Log.d("clock", "3" + " c = " + counter.toString())
            onRestStartWatch()
            onPauseWatch()
        } else if((((timeCount / 2) + ((timeCount / 2) / 2)) < time) && (counter == 1)) {
            counter++
            Log.d("clock", "2" + " c = " + counter.toString())
            onRestStartWatch()
            onPauseWatch()
        } else if(((timeCount / 2) < time) && (counter == 0)) {
            counter++
            Log.d("clock", "1" + " c = " + counter.toString())
            onRestStartWatch()
            onPauseWatch()
        }
    }

    LaunchedEffect(restTime) {
        if (restTime > 10000L) {
            onRestResetWatch()
            onStartWatch()
        }
    }



    val stroke = 8.dp
    val color = Color.Blue

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Box(modifier = Modifier
            .size(200.dp)
            .clip(CircleShape)
            .clickable {
                if (!isPlay) {
                    onStartWatch()
                } else {
                    onPauseWatch()
                }
                isPlay = !isPlay
            }
        ) {
           AnimatedVisibility(visible = isPlay) {
               Icon(
                   modifier = Modifier
                       .size(200.dp),
                   imageVector = Icons.Filled.Close,
                   contentDescription = null,
                   tint = Color(0,0,0,20)
               )
           }
            AnimatedVisibility(visible = !isPlay) {
                Icon(
                    modifier = Modifier
                        .size(200.dp),
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = null,
                    tint = Color(0,0,0,20)
                )
            }
        }
        Canvas(modifier = Modifier.size(280.dp)) {
            val innerRadius = (size.minDimension - stroke.toPx()) / 2
            val innerRadiusSecond = (size.minDimension - stroke.toPx()) / 2.2f
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
            //.875
            val startAngle = -90f
            val sweep = (time / timeCount) * 360f
            drawArc(
                color = Color.LightGray,
                startAngle = startAngle,
                sweepAngle = 360f,
                topLeft = topLeftSecond,
                size = sizeSecond,
                useCenter = false,
                style = Stroke(width = stroke.toPx(), cap = StrokeCap.Round)
            )
            if(restTime > 0L) {
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
                color = Color.LightGray,
                startAngle = startAngle,
                sweepAngle = 173f,
                topLeft = topLeft,
                size = size,
                useCenter = false,
                style = Stroke(width = stroke.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = Color.LightGray,
                startAngle = 91f,
                sweepAngle = 1f,
                topLeft = topLeft,
                size = size,
                useCenter = false,
                style = Stroke(width = stroke.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = Color.LightGray,
                startAngle = 100f,
                sweepAngle = 73f,
                topLeft = topLeft,
                size = size,
                useCenter = false,
                style = Stroke(width = stroke.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = Color.LightGray,
                startAngle = 181f,
                sweepAngle = 1f,
                topLeft = topLeft,
                size = size,
                useCenter = false,
                style = Stroke(width = stroke.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = Color.LightGray,
                startAngle = 190f,
                sweepAngle = 28f,
                topLeft = topLeft,
                size = size,
                useCenter = false,
                style = Stroke(width = stroke.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = Color.LightGray,
                startAngle = 226f,
                sweepAngle = 1f,
                topLeft = topLeft,
                size = size,
                useCenter = false,
                style = Stroke(width = stroke.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = Color.LightGray,
                startAngle = 235f,
                sweepAngle = 27f,
                topLeft = topLeft,
                size = size,
                useCenter = false,
                style = Stroke(width = stroke.toPx(), cap = StrokeCap.Round)
            )
            if(time > 0L) {
                drawArc(
                    color = color,
                    startAngle = startAngle,
                    sweepAngle = (time.toFloat() / timeCount)  * 360f,
                    topLeft = topLeft,
                    size = size,
                    useCenter = false,
                    style = Stroke(width = stroke.toPx(), cap = StrokeCap.Round)
                )
            }
        }
        val num = (time.toFloat() / Math.abs(timeCount.toFloat()))
        val df = DecimalFormat("#.###")
        val roundOff = df.format(num)
        Text(text = "${formattedTime}", style = TextStyle(
            fontSize = 34.sp, fontWeight = FontWeight.Black
        ))
    }


}