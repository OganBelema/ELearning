package com.oganbelema.elearning.factory

import com.oganbelema.database.entity.LessonEntity
import com.oganbelema.network.model.Lesson

/**
 * Created by Belema Ogan on 1/18/21.
 */
internal object LessonFactory {
    fun makeLessonEntity(): LessonEntity {
        return with(DataFactory){
            LessonEntity(randomLong(), randomString(), randomString(), randomString(), randomLong(),
                randomLong())
        }
    }

    fun makeLesson(): Lesson {
        return  with(DataFactory){
            Lesson(randomLong(), randomString(), randomString(), randomString(), randomLong(),
                randomLong())
        }
    }
}