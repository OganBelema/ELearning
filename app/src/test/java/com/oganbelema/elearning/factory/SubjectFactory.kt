package com.oganbelema.elearning.factory

import com.oganbelema.database.entity.SubjectEntity
import com.oganbelema.network.model.Subject


/**
 * Created by Belema Ogan on 1/18/21.
 */
object SubjectFactory {

    fun makeSubjectEntity(): SubjectEntity {
        return with(DataFactory){
            SubjectEntity(randomLong(), randomString(), randomString(),
                listOf(ChapterFactory.makeChapterEntity()))
        }
    }

    fun makeSubject(): Subject {
        return  with(DataFactory){
            Subject(randomLong(), randomString(), randomString(),
                listOf(ChapterFactory.makeChapter()))
        }
    }
}