package com.oganbelema.elearning.factory

import com.oganbelema.database.entity.ChapterEntity
import com.oganbelema.network.model.Chapter


/**
 * Created by Belema Ogan on 1/18/21.
 */
object ChapterFactory {

    fun makeChapterEntity(): ChapterEntity {
        return with(DataFactory){
            ChapterEntity(randomLong(), randomString(), listOf(LessonFactory.makeLessonEntity()))
        }
    }

    fun makeChapter(): Chapter {
        return  with(DataFactory){
            Chapter(randomLong(), randomString(), listOf(LessonFactory.makeLesson()))
        }
    }
}