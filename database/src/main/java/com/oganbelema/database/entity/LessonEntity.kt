package com.oganbelema.database.entity

import androidx.annotation.NonNull
import androidx.room.*
import androidx.room.ForeignKey.CASCADE


/**
 * Created by Belema Ogan on 1/14/21.
 */

@Entity(tableName = "lesson")
data class LessonEntity (
    @PrimaryKey
    @NonNull
    val id: Long,
    val name: String,
    val icon: String,

    @ColumnInfo(name = "media_url")
    val mediaURL: String,

    @ColumnInfo(name = "subject_id")
    val subjectID: Long,

    @ColumnInfo(name = "chapter_id")
    val chapterID: Long
)