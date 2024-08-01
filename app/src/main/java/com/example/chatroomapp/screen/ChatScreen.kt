package com.example.chatroomapp.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun ChatScreen(roomId : String) {

    val text = remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = text.value,
                onValueChange = {text.value = it},
                textStyle = TextStyle.Default.copy(fontSize = 16.sp),
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            )
            IconButton(onClick = {
                if (text.value.isNotEmpty()) {
                    text.value = ""
                }
            }
            ) {
                Icon(imageVector = Icons.Default.Send, contentDescription = "Send")
            }
        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
private fun formatTimestamp(timestamp: Long) : String {

    val messageDateTime = LocalDateTime.ofInstant(
        Instant.ofEpochMilli(timestamp),
        ZoneId.systemDefault()
    )
    val now = LocalDateTime.now()

    return when {
        isSameDay(messageDateTime, now)
        -> "today ${formatTime(messageDateTime)}"
        isSameDay(messageDateTime.minusDays(1), now)
        -> "yesterday ${formatTime(messageDateTime)}"
        else -> formatTime(messageDateTime)

    }
}
@RequiresApi(Build.VERSION_CODES.O)
private fun isSameDay(dateTime1 : LocalDateTime, dateTime2 : LocalDateTime) : Boolean {

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return dateTime1.format(formatter) == dateTime2.format(formatter)
}
@RequiresApi(Build.VERSION_CODES.O)
private fun formatTime(dateTime : LocalDateTime) : String {

    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    return formatter.format(dateTime)
}



@Preview
@Composable
fun ChatScreenPreview(){
    ChatScreen(roomId = "")
}

















