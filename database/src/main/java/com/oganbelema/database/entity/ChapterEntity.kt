package com.oganbelema.database.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.oganbelema.database.typeconverter.LessonTypeConverter


/**
 * Created by Belema Ogan on 1/14/21.
 */

@Entity(tableName = "chapter")
data class ChapterEntity (
    @PrimaryKey
    @NonNull
    val id: Long,
    val name: String,
    @TypeConverters(LessonTypeConverter::class)
    val lessons: List<LessonEntity>
)