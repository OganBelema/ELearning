package com.oganbelema.elearning.mapper

import com.oganbelema.database.entity.LessonEntity
import com.oganbelema.network.model.Lesson
import javax.inject.Inject

/**
 * Created by Belema Ogan on 1/18/21.
 */
class LessonMapper @Inject constructor(): EntityMapper<LessonEntity, Lesson> {

    override fun fromEntity(entity: LessonEntity): Lesson {
        return Lesson(entity.id, entity.name, entity.icon, entity.mediaURL, entity.subjectID,
            entity.chapterID)
    }

    override fun toEntity(model: Lesson): LessonEntity {
        return LessonEntity(model.id, model.name, model.icon, model.mediaURL, model.subjectID,
            model.chapterID)
    }

    override fun fromEntityList(entities: List<LessonEntity>?): List<Lesson>? {
        return if (entities != null){
            val lessons = ArrayList<Lesson>()
            for (lesson in entities){
                lessons.add(fromEntity(lesson))
            }
            lessons
        } else {
            null
        }
    }

    override fun toEntityList(models: List<Lesson>?): List<LessonEntity>? {
        return if (models != null){
            val lessonEntities = ArrayList<LessonEntity>()
            for (lessonEntity in models){
                lessonEntities.add(toEntity(lessonEntity))
            }
            lessonEntities
        } else {
            null
        }
    }
}