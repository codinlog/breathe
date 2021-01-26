package com.codinlog.breathe

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd:hh-mm-ss")
fun convertToLocalTime(mills: Long): String =
    LocalDateTime.ofInstant(Instant.ofEpochMilli(mills), ZoneId.systemDefault())
        .format(formatter)