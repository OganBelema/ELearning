package com.oganbelema.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.oganbelema.database.dao.SubjectDao
import com.oganbelema.database.dao.TopicDao
import com.oganbelema.database.entity.ChapterEntity
import com.oganbelema.database.entity.LessonEntity
import com.oganbelema.database.entity.SubjectEntity
import com.oganbelema.database.entity.TopicEntity
import com.oganbelema.database.typeconverter.ChapterTypeConverter
import com.oganbelema.database.typeconverter.LessonTypeConverter
import com.oganbelema.database.typeconverter.TimestampConverter

/**
 * Created by Belema Ogan on 1/17/21.
 */
@Database(entities = [ChapterEntity::class, LessonEntity::class, SubjectEntity::class,
    TopicEntity::class], version = 1, exportSchema = false)
@TypeConverters(ChapterTypeConverter::class, LessonTypeConverter::class, TimestampConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun subjectDao(): SubjectDao
    abstract fun topicDao(): TopicDao
}