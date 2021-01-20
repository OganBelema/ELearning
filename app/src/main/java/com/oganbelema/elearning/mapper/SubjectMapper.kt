package com.oganbelema.elearning.mapper

import com.oganbelema.database.entity.SubjectEntity
import com.oganbelema.network.model.Subject
import javax.inject.Inject

/**
 * Created by Belema Ogan on 1/18/21.
 */
class SubjectMapper @Inject constructor(private val chapterMapper: ChapterMapper):
    EntityMapper<SubjectEntity, Subject> {

    override fun fromEntity(entity: SubjectEntity): Subject {
        return Subject(entity.id, entity.name, entity.icon,
            chapterMapper.fromEntityList(entity.chapters) ?: listOf())
    }

    override fun toEntity(model: Subject): SubjectEntity {
        return SubjectEntity(model.id, model.name, model.icon,
            chapterMapper.toEntityList(model.chapters) ?: listOf())
    }

    override fun fromEntityList(entities: List<SubjectEntity>?): List<Subject>? {
        return if (entities != null) {
            val subjects = ArrayList<Subject>()
            for (subjectEntity in entities) {
                subjects.add(fromEntity(subjectEntity))
            }
            subjects
        } else {
            null
        }
    }

    override fun toEntityList(models: List<Subject>?): List<SubjectEntity>? {
        return if (models != null) {
            val subjectEntities = ArrayList<SubjectEntity>()
            for (subject in models){
                subjectEntities.add(toEntity(subject))
            }
            subjectEntities
        } else {
            null
        }
    }
}