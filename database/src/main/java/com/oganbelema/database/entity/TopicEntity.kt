package com.oganbelema.database.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.oganbelema.database.typeconverter.TimestampConverter
import java.util.*

/**
 * Created by Belema Ogan on 1/19/21.
 */
@Entity(tableName = "topic")
data class TopicEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "lesson_id")
    val lessonId: Long,
    @ColumnInfo(name = "lesson_name")
    val lessonName: String,
    @ColumnInfo(name = "subject_name")
    val subjectName: String,
    @ColumnInfo(name = "lesson_media_url")
    val lessonMediaUrl: String,
    @ColumnInfo(name = "date")
    @TypeConverters(TimestampConverter::class)
    val date: Date
)