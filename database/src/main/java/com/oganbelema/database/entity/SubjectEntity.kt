package com.oganbelema.database.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.oganbelema.database.typeconverter.ChapterTypeConverter


/**
 * Created by Belema Ogan on 1/14/21.
 */

@Entity(tableName = "subject")
data class SubjectEntity (
    @PrimaryKey
    @NonNull
    val id: Long,
    val name: String,
    val icon: String,
    @TypeConverters(ChapterTypeConverter::class)
    val chapters: List<ChapterEntity>
)