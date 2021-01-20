package com.oganbelema.elearning.mapper

import com.oganbelema.database.entity.ChapterEntity
import com.oganbelema.network.model.Chapter
import javax.inject.Inject

/**
 * Created by Belema Ogan on 1/18/21.
 */
class ChapterMapper @Inject constructor(private val lessonMapper: LessonMapper):
    EntityMapper<ChapterEntity, Chapter> {

    override fun fromEntity(entity: ChapterEntity): Chapter {
        return Chapter(entity.id, entity.name,
            lessonMapper.fromEntityList(entity.lessons) ?: listOf())
    }

    override fun toEntity(model: Chapter): ChapterEntity {
        return ChapterEntity(model.id, model.name,
            lessonMapper.toEntityList(model.lessons) ?: listOf())
    }

    override fun fromEntityList(entities: List<ChapterEntity>?): List<Chapter>? {
        return if (entities != null){
            val chapters = ArrayList<Chapter>()
            for (chapterEntity in entities){
                chapters.add(fromEntity(chapterEntity))
            }
            chapters
        } else {
            null
        }
    }

    override fun toEntityList(models: List<Chapter>?): List<ChapterEntity>? {
        return if (models != null){
            val chapterEntities = ArrayList<ChapterEntity>()
            for (chapter in models){
                chapterEntities.add(toEntity(chapter))
            }
            chapterEntities
        } else {
            null
        }
    }
}